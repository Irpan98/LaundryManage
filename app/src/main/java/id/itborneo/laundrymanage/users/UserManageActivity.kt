package id.itborneo.laundrymanage.users

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.R
import id.itborneo.laundrymanage.networks.ApiClient
import kotlinx.android.synthetic.main.activity_user_manage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserManageActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_manage)

        setupActionbar()
        initRecycleriew()
        getData()
    }

    //mengatur toolbar
    private fun setupActionbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "User "
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
        adapter = UserAdapter {

        }
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(this)
    }


    private fun getData() {

        ApiClient.create()
            .getUsers().enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    val list = response.body()?.data
                    if (list != null) {
                        adapter.list = list as List<UsersModel>
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("UserManageActivity", "list is null")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("UserManageActivity", "getPacket onFailure")
                }

            })

    }
}