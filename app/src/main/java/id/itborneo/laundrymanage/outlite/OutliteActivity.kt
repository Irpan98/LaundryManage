package id.itborneo.laundrymanage.outlite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.laundrymanage.networks.ApiClient
import id.itborneo.laundrymanage.R
import kotlinx.android.synthetic.main.activity_outlite.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OutliteActivity : AppCompatActivity() {

    private lateinit var adapter: OutliteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outlite)



        initRecyclerview()
        observerData()
    }

    private fun initRecyclerview() {
        adapter = OutliteAdapter {

        }
        rvOutline.adapter = adapter
        rvOutline.layoutManager = LinearLayoutManager(this)
    }

    private fun observerData() {
        ApiClient.create()
            .getOutline().enqueue(object : Callback<OutliteResponse> {
                override fun onResponse(
                    call: Call<OutliteResponse>,
                    response: Response<OutliteResponse>
                ) {
                    val list = response.body()?.data
                    if (list != null) {
                        adapter.list = list
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.d("OutliteActivity", "list is null")
                    }
                }

                override fun onFailure(call: Call<OutliteResponse>, t: Throwable) {
                    Log.d("OutliteActivity", "getOutline onFailure")
                }

            })

    }
}
