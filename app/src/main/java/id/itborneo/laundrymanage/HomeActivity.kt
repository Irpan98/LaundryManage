package id.itborneo.laundrymanage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.itborneo.laundrymanage.login.LoginActivity.Companion.EXTRA_USER
import id.itborneo.laundrymanage.packet.PacketActivity
import id.itborneo.laundrymanage.transaction.TransactionActivity
import id.itborneo.laundrymanage.users.UserManageActivity
import id.itborneo.laundrymanage.users.UsersModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.partial_home_packet_and_customer.*
import kotlinx.android.synthetic.main.partial_home_transaction_and_report.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setupActionbar()
        buttonListener()
        retrieveData()
    }


    private fun retrieveData() {
        val data = intent.getParcelableExtra<UsersModel>(EXTRA_USER)
        if (data != null) {
            tvUserName.text = data.nama
            updatePrivilege(data)
        }
    }

    private fun updatePrivilege(user: UsersModel) {
        when (user.role) {
            "Kasir" -> {
                
                incLine2.visibility = View.GONE
            }
            "Owner" -> {
                incLine2.visibility = View.GONE
            }
        }
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }

    private fun buttonListener() {
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
            dialogUnderConstruction(this, rootView)
        }
    }

    private fun dialogUnderConstruction(context: Context, rootView: ViewGroup) {
        val dialog = BottomSheetDialog(context)
        val view =
            LayoutInflater.from(context).inflate(R.layout.partial_on_development, rootView, false)

        view.findViewById<Button>(R.id.btnClose).setOnClickListener {
            dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }
}