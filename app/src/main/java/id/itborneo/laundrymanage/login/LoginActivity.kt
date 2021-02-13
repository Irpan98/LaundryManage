package id.itborneo.laundrymanage.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import id.itborneo.laundrymanage.HomeActivity
import id.itborneo.laundrymanage.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupActionbar()
        buttonListener()
    }

    private fun buttonListener() {
        btnLogin.setOnClickListener {
            finish()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }
}