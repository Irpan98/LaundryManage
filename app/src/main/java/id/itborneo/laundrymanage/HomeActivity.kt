package id.itborneo.laundrymanage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.itborneo.laundrymanage.outlite.OutliteActivity
import kotlinx.android.synthetic.main.activity_home.*

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
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
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