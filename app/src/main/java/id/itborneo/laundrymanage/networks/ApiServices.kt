package id.itborneo.laundrymanage.networks


import id.itborneo.laundrymanage.packet.PacketResponse
import id.itborneo.laundrymanage.transaction.TransactionResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    //PACKET
    @GET("getPacket.php")
    fun getPacket(): Call<PacketResponse>

    @FormUrlEncoded
    @POST("insertPacket.php")
    fun addPacket(
        @Field("name") name: String,
        @Field("price") price: Int,
        @Field("note") note: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("updatePacket.php")
    fun updatePacket(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("price") price: Int,
        @Field("note") note: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("deletePacket.php")
    fun deletePacket(
        @Field("id") id: Int,
    ): Call<DefaultResponse>
    //END PACKET

    //TRANSACTION

    @GET("transaction/getTransaction.php")
    fun getTransaction(): Call<TransactionResponse>

    @FormUrlEncoded
    @POST("transaction/insertTransaction.php")
    fun addTransaction(
        @Field("customer_name") customerName: String,
        @Field("qty") qty: String,
        @Field("status_progress") statusProgress: String,
        @Field("status_payment") statusPayment: String,
        @Field("total_price") totalPrice: String,
        @Field("id_packet") idPacket: String,
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("transaction/updateTransaction.php")
    fun updateTransaction(
        @Field("id") id: Int,
        @Field("qty") qty: String,
        @Field("status_progress") statusProgress: Int,
        @Field("status_payment") statusPayment: String,
        @Field("total_price") totalPrice: String,
        @Field("id_packet") idPacket: String,
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("transaction/deleteTransaction.php")
    fun deleteTransaction(
        @Field("id") id: Int,
    ): Call<DefaultResponse>
    //END TRANSACTION


}