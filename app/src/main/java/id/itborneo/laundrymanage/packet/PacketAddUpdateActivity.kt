package id.itborneo.laundrymanage.packet

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.networks.ApiClient
import id.itborneo.laundrymanage.networks.DefaultResponse
import id.itborneo.laundrymanage.packet.PacketActivity.Companion.EXTRA_PACKET
import kotlinx.android.synthetic.main.activity_packet_add_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PacketAddUpdateActivity : AppCompatActivity() {

    private var isAddData = true
    private var data: PacketModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packet_add_update)

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
    }

    private fun addData() {

        ApiClient.create()
            .addPacket(
                etName.text.toString(),
                etPrice.text.toString().toInt(),
                etNote.text.toString()
            )
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(
                        this@PacketAddUpdateActivity,
                        "Berhasil Menambah Packet",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK, intent)
                    finish()
                    Log.d("PacketAddUpdateActivity", "berhasil add data ")
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("PacketAddUpdateActivity", "gagal add data")
                }
            })
    }

    private fun updateData() {

        ApiClient.create()
            .updatePacket(
                data?.id!!,
                etName.text.toString(),
                etPrice.text.toString().toInt(),
                etNote.text.toString()
            )
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(
                        this@PacketAddUpdateActivity,
                        "Berhasil Update Packet",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK, intent)
                    finish()
                    Log.d("PacketAddUpdateActivity", "berhasil Update data ")
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("PacketAddUpdateActivity", "gagal Update data")
                }
            })
    }

    private fun deleteData() {
        ApiClient.create()
            .deletePacket(data?.id!!)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(
                        this@PacketAddUpdateActivity,
                        "Berhasil Hapus Packet",
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(RESULT_OK, intent)
                    finish()
                    Log.d("PacketAddUpdateActivity", "berhasil Hapus data ")
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("PacketAddUpdateActivity", "gagal Hapus data")
                }
            })
    }

    private fun retrieveData() {
        data = intent.getParcelableExtra(EXTRA_PACKET)
        if (data != null) {
            isAddData = false
            updateField()
        }else{
            btnDelete.visibility = View.GONE

        }
    }

    private fun updateField() {
        etName.setText(data?.name)
        etPrice.setText(data?.price)
        etNote.setText(data?.note)

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


}