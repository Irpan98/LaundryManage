package id.itborneo.laundrymanage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.itborneo.laundrymanage.outlite.OutliteActivity
import id.itborneo.laundrymanage.packet.PacketActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.partial_home_packet_and_customer.*
import kotlinx.android.synthetic.main.partial_home_transaction_and_report.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        buttonListener()
    }

    private fun buttonListener() {
        btnOutlite.setOnClickListener {
            val intent = Intent(this, OutliteActivity::class.java)
            startActivity(intent)
        }
        btnProduct.setOnClickListener {
            val intent = Intent(this, PacketActivity::class.java)
            startActivity(intent)
        }

        btnUserManage.setOnClickListener {
            val intent = Intent(this, UserManageActivity::class.java)
            startActivity(intent)
        }

        btnTransaction.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }

        btnReport.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }
}