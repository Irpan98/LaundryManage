package id.itborneo.laundrymanage.packet

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.utils.toRupiah
import kotlinx.android.synthetic.main.activity_packet.*


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

        val dummyData = mutableListOf<PacketModel>()

        repeat(10) {
            dummyData.add(
                PacketModel(
                    null,
                    "packet $it",
                    "40000".toRupiah(),
                    "catatan ku adasjkdas askdj kasjdkas kjas dkasj dkasj"
                )
            )
        }

        adapter.list = dummyData
        adapter.notifyDataSetChanged()

//        ApiClient.create()
//            .getPacket().enqueue(object : Callback<OutliteResponse> {
//                override fun onResponse(
//                    call: Call<OutliteResponse>,
//                    response: Response<OutliteResponse>
//                ) {
//                    val list = response.body()?.data
//                    if (list != null) {
//                        adapter.list = list
//                        adapter.notifyDataSetChanged()
//                    } else {
//                        Log.d("PacketActivity", "list is null")
//                    }
//                }
//
//                override fun onFailure(call: Call<OutliteResponse>, t: Throwable) {
//                    Log.d("PacketActivity", "getPacket onFailure")
//                }
//
//            })

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

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)

    }

}