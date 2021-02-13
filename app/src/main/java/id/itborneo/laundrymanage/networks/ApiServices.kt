package id.itborneo.laundrymanage.networks


import id.itborneo.laundrymanage.outlite.OutliteResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("getOutlet.php")
    fun getOutline(): Call<OutliteResponse>

    @GET("getOutlet.php")
    fun getPacket(): Call<OutliteResponse>

}