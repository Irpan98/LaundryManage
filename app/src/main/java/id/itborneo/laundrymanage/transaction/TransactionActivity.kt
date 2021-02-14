package id.itborneo.laundrymanage.transaction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.networks.ApiClient
import id.itborneo.laundrymanage.packet.PacketModel
import id.itborneo.laundrymanage.packet.PacketResponse
import kotlinx.android.synthetic.main.activity_transaction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionActivity : AppCompatActivity() {

    private lateinit var adapter: TransactionAdapter

    companion object {
        private const val REQ_ADD = 30
        private const val REQ_UPDATE = 20

        const val EXTRA_TRANSACTION = "transaction"
        const val EXTRA_PACKET = "packet"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        buttonListener()
        setupActionbar()
        initRecycleriew()
        getData()

    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Transaksi"
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

    //setup awal untuk list data
    private fun initRecycleriew() {
        adapter = TransactionAdapter {

            ApiClient.create()
                .getPacket().enqueue(object : Callback<PacketResponse> {
                    override fun onResponse(
                        call: Call<PacketResponse>,
                        response: Response<PacketResponse>
                    ) {
                        val list = response.body()?.data
                        if (list != null) {

                            val array = ArrayList<PacketModel?>()

                            list.forEach {
                                array.add(it)
                            }

                            val intent = Intent(this@TransactionActivity , TransactionAddUpdateActivity::class.java)

                            intent.putParcelableArrayListExtra(EXTRA_PACKET, array)
                            intent.putExtra(EXTRA_TRANSACTION, it)

                            startActivityForResult(intent, REQ_UPDATE)

                        } else {
                            Log.d("TransactionActivity", "list is null")
                        }
                    }

                    override fun onFailure(call: Call<PacketResponse>, t: Throwable) {
                        Log.d("TransactionActivity", "getPacket onFailure")
                    }

                })


        }
        rvTransaction.adapter = adapter
        rvTransaction.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        ApiClient.create()
            .getTransaction().enqueue(object : Callback<TransactionResponse> {
                override fun onResponse(
                    call: Call<TransactionResponse>,
                    response: Response<TransactionResponse>
                ) {
                    val list = response.body()?.data
                    if (list != null) {
                        adapter.list = list as List<TransactionModel>
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("TransactionActivity", "list is null")
                    }
                }

                override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                    Log.d("TransactionActivity", "getPacket onFailure")
                }

            })

    }

//    private fun getData() {
//
//        val dummyData = mutableListOf<TransactionModel>()
//
//        repeat(10) {
//            dummyData.add(
//                TransactionModel(
//                    it.toString(),
//                    "Irpan $it",
//                    ProgressStatusEnum.Proses.name,
//                    "40000",
//                    PaymentStatusEnum.Dibayar.name,
//                    "1"
//                )
//            )
//        }
//
//        adapter.list = dummyData
//        adapter.notifyDataSetChanged()
//    }

    private fun buttonListener() {
        fabAdd.setOnClickListener {

            ApiClient.create()
                .getPacket().enqueue(object : Callback<PacketResponse> {
                    override fun onResponse(
                        call: Call<PacketResponse>,
                        response: Response<PacketResponse>
                    ) {
                        val list = response.body()?.data
                        if (list != null) {

                            val array = ArrayList<PacketModel?>()

                            list.forEach {
                                array.add(it)
                            }


                            val intent = Intent(
                                this@TransactionActivity,
                                TransactionAddUpdateActivity::class.java
                            )
                            intent.putParcelableArrayListExtra(EXTRA_PACKET, array)
                            startActivityForResult(intent, REQ_ADD)

                        } else {
                            Log.d("TransactionActivity", "list is null")
                        }
                    }

                    override fun onFailure(call: Call<PacketResponse>, t: Throwable) {
                        Log.d("TransactionActivity", "getPacket onFailure")
                    }

                })

        }
    }
}