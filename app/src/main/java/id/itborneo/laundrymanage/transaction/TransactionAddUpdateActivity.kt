package id.itborneo.laundrymanage.transaction

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.enums.PaymentStatusEnum
import id.itborneo.laundrymanage.enums.ProgressStatusEnum
import id.itborneo.laundrymanage.networks.ApiClient
import id.itborneo.laundrymanage.networks.DefaultResponse
import id.itborneo.laundrymanage.packet.PacketModel
import id.itborneo.laundrymanage.transaction.TransactionActivity.Companion.EXTRA_PACKET
import id.itborneo.laundrymanage.transaction.TransactionActivity.Companion.EXTRA_TRANSACTION
import kotlinx.android.synthetic.main.activity_packet_add_update.*
import kotlinx.android.synthetic.main.activity_packet_add_update.btnAddUpdate
import kotlinx.android.synthetic.main.activity_packet_add_update.btnDelete
import kotlinx.android.synthetic.main.activity_transaction_add_update.*
import kotlinx.android.synthetic.main.activity_transaction_add_update.view.*
import kotlinx.android.synthetic.main.item_transaction.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionAddUpdateActivity : AppCompatActivity() {

    private var isAddData = true
    private var data: TransactionModel? = null

    private var paymentStatus: String? = null
    private var packetId: String? = null
    private var price: String? = null
    private var progressStatus: String? = null
    private lateinit var listPacket: ArrayList<PacketModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_add_update)
        retrieveData()
        setupActionbar()
        buttonListener()
    }

    private fun buttonListener() {
        btnAddUpdate.setOnClickListener {
            if (isAddData) {
                addData()
            } else {
                updateData()
            }
        }

        btnDelete.setOnClickListener {
            deleteData()
        }

        BtnGroupPaymentStatus.addOnButtonCheckedListener { group, checkedId, isChecked ->

            if (isChecked) {
                paymentStatus = if (checkedId == R.id.BtnPaymentPaid) {
                    PaymentStatusEnum.Dibayar.name
                } else {
                    PaymentStatusEnum.Belum_Dibayar.name

                }
            }

        }

        btnGroupProgress.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                progressStatus = when (checkedId) {
                    R.id.btnProgressProgress -> {
                        ProgressStatusEnum.Proses.name
                    }
                    R.id.btnProgressNew -> {
                        ProgressStatusEnum.Baru.name

                    }
                    R.id.btnProgressDone -> {
                        ProgressStatusEnum.Selesai.name

                    }
                    else -> {
                        ProgressStatusEnum.Diambil.name

                    }
                }
            }

        }
    }

    private fun retrieveData() {
        data = intent.getParcelableExtra(EXTRA_TRANSACTION)
        Log.d("retrieveData data", "$data ")

        val listPacketIntent = intent.getParcelableArrayListExtra<PacketModel?>(EXTRA_PACKET)
        if (listPacketIntent != null) {
            listPacket = listPacketIntent
        }

        if (data != null) {
            isAddData = false
            updateField()

        } else {
            btnDelete.visibility = View.GONE
            btnGroupProgress.visibility = View.GONE

        }


        dropDownPacketList(listPacketIntent)

    }

    private fun dropDownPacketList(listpacket: ArrayList<PacketModel>?) {

        val items = mutableListOf<String>()

        listpacket?.forEach {
            items.add(it.name ?: "")

        }

        Log.d("retrieveData listpacket", "$items ")


        val listPopupWindow = ListPopupWindow(this, null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = btnPopUpPacket

        val adapter = ArrayAdapter(this, R.layout.list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            // Respond to list popup window item click.

            btnPopUpPacket.text = listpacket?.get(position)?.name.toString()
            packetId = listpacket?.get(position)?.id.toString()
            price = listpacket?.get(position)?.price
            // Dismiss popup.
            listPopupWindow.dismiss()
        }

// Show list popup window on button click.
        btnPopUpPacket.setOnClickListener {

            Log.d("listPopupWindowButton", "setOnClickListene ")

            listPopupWindow.show()
        }
    }


    private fun updateField() {
        etCustomerName.setText(data?.customer_name)
        paymentStatus = data?.status_payment
        packetId = data?.id_packet
        price = data?.total_price
        progressStatus = data?.status_progress

        listPacket.forEach {
            if (it.id.toString() == data?.id_packet) {
                btnPopUpPacket.text = it.name
                return@forEach
            }
        }

        etQty.setText(data?.qty)

        if (data?.status_payment == PaymentStatusEnum.Dibayar.name) {
            BtnGroupPaymentStatus.check(R.id.BtnPaymentPaid)
        } else {
            BtnGroupPaymentStatus.check(R.id.BtnPaymentNotPaid)
        }

        when (data?.status_progress) {
            ProgressStatusEnum.Baru.name -> btnGroupProgress.check(R.id.btnProgressNew)
            ProgressStatusEnum.Proses.name -> btnGroupProgress.check(R.id.btnProgressProgress)
            ProgressStatusEnum.Selesai.name -> btnGroupProgress.check(R.id.btnProgressDone)
            else -> btnGroupProgress.check(R.id.btnProgressTaken)


        }

//        tvIdTransaction.text = data.id.toString()
//        tvProgressStatus.text = data.progress_status
//        tvTotalPrice.text = data.totalPrice?.toRupiah()
//        tvPaymentStatus.text = data.payment_status
//        tvCustomerName.text = data.customer_name

        btnAddUpdate.text = "Update Paket"
        btnAddUpdate.icon = ContextCompat.getDrawable(this, R.drawable.ic_update)
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = if (isAddData) {
            "Tambah Paket"
        } else {
            "Update paket"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //action ketika tombol back ditekan di toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addData() {

        ApiClient.create()
            .addTransaction(
                etCustomerName.text.toString(),
                etQty.text.toString(),
                ProgressStatusEnum.Baru.name,
                paymentStatus ?: PaymentStatusEnum.Belum_Dibayar.name,
                (etQty.text.toString().toInt() * price?.toInt()!!).toString(),
                packetId!!

            )
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(
                        this@TransactionAddUpdateActivity,
                        "Berhasil Menambah Transaksi",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK, intent)
                    finish()
                    Log.d("TransactionAddUpdate", "berhasil add data ")
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("TransactionAddUpdate", "gagal add data")
                }
            })
    }

    private fun updateData() {

//        ApiClient.create()
//            .updatePacket(
//                data?.id!!,
//                etName.text.toString(),
//                etPrice.text.toString().toInt(),
//                etNote.text.toString()
//            )
//            .enqueue(object : Callback<DefaultResponse> {
//                override fun onResponse(
//                    call: Call<DefaultResponse>,
//                    response: Response<DefaultResponse>
//                ) {
//                    Toast.makeText(
//                        this@PacketAddUpdateActivity,
//                        "Berhasil Update Packet",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    setResult(RESULT_OK, intent)
//                    finish()
//                    Log.d("PacketAddUpdateActivity", "berhasil Update data ")
//                }
//
//                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                    Log.d("PacketAddUpdateActivity", "gagal Update data")
//                }
//            })
    }

    private fun deleteData() {
//        ApiClient.create()
//            .deletePacket(data?.id!!)
//            .enqueue(object : Callback<DefaultResponse> {
//                override fun onResponse(
//                    call: Call<DefaultResponse>,
//                    response: Response<DefaultResponse>
//                ) {
//                    Toast.makeText(
//                        this@PacketAddUpdateActivity,
//                        "Berhasil Hapus Packet",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    setResult(RESULT_OK, intent)
//                    finish()
//                    Log.d("PacketAddUpdateActivity", "berhasil Hapus data ")
//                }
//
//                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                    Log.d("PacketAddUpdateActivity", "gagal Hapus data")
//                }
//            })
    }
}