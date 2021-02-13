package id.itborneo.laundrymanage.transaction

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.enums.PaymentStatusEnum
import id.itborneo.laundrymanage.enums.ProgressStatusEnum
import id.itborneo.laundrymanage.utils.toRupiah
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    private lateinit var adapter: TransactionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)


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
//            val intent = Intent(this, PacketAddUpdateActivity::class.java)
//            intent.putExtra(PacketActivity.EXTRA_PACKET, it)
//            startActivityForResult(intent, PacketActivity.REQ_UPDATE)
        }
        rvTransaction.adapter = adapter
        rvTransaction.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {

        val dummyData = mutableListOf<TransactionModel>()

        repeat(10) {
            dummyData.add(
                TransactionModel(
                    it.toString(),
                    "packet $it",
                    ProgressStatusEnum.Proses.name,
                    "40000",
                    PaymentStatusEnum.Dibayar.name,
                    "1"
                )
            )
        }

        adapter.list = dummyData
        adapter.notifyDataSetChanged()
    }
}