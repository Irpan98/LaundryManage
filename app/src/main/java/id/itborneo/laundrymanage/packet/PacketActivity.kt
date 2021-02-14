package id.itborneo.laundrymanage.packet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.networks.ApiClient
import kotlinx.android.synthetic.main.activity_packet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PacketActivity : AppCompatActivity() {

    private lateinit var adapter: PacketAdapter

    companion object {
        private const val REQ_ADD = 30
        private const val REQ_UPDATE = 20

        const val EXTRA_PACKET = "packet"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packet)

        buttonListener()
        setupActionbar()
        initRecycleriew()
        getData()
    }

    //setup awal untuk list data
    private fun initRecycleriew() {
        adapter = PacketAdapter {
            val intent = Intent(this, PacketAddUpdateActivity::class.java)
            intent.putExtra(EXTRA_PACKET, it)
            startActivityForResult(intent, REQ_UPDATE)
        }
        rvPacket.adapter = adapter
        rvPacket.layoutManager = LinearLayoutManager(this)
    }


    private fun getData() {

        ApiClient.create()
            .getPacket().enqueue(object : Callback<PacketResponse> {
                override fun onResponse(
                    call: Call<PacketResponse>,
                    response: Response<PacketResponse>
                ) {
                    val list = response.body()?.data
                    if (list != null) {
                        adapter.list = list as List<PacketModel>
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("PacketActivity", "list is null")
                    }
                }

                override fun onFailure(call: Call<PacketResponse>, t: Throwable) {
                    Log.d("PacketActivity", "getPacket onFailure")
                }

            })

    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Paket"
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

    //setiap tombol ada disini
    private fun buttonListener() {
        fabAdd.setOnClickListener {
            val intent = Intent(this, PacketAddUpdateActivity::class.java)
            startActivityForResult(intent, REQ_ADD)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("onActivityResult","called")
        getData()

    }

}