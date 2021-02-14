package id.itborneo.laundrymanage.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import id.itborneo.laundrymanage.HomeActivity
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.networks.ApiClient
import id.itborneo.laundrymanage.users.UserResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupActionbar()
        buttonListener()
    }

    private fun buttonListener() {
        btnLogin.setOnClickListener {

            ApiClient.create()
                .login(etEmail.text.toString()).enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        val list = response.body()?.data
                        if (list != null) {

                            list.forEach {
                                if (it?.password?.equals(etPassword.text.toString())!!) {
                                    finish()
                                    val intent =
                                        Intent(this@LoginActivity, HomeActivity::class.java)
                                    intent.putExtra(EXTRA_USER, it)
                                    startActivity(intent)

                                } else {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Email atau Password Salah",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email atau Password Salah",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("LoginActivity", "getPacket onFailure")
                    }

                })

        }
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }


}