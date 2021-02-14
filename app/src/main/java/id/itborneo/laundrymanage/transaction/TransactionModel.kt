package id.itborneo.laundrymanage.transaction

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionModel(
    val id: String? = null,
    val customer_name: String?,
    val status_progress: String?,
    val total_price: String?,
    val status_payment: String?,
    val id_packet: String?,

    val id_member: Int? = null,
    val idUser: Int? = null,
    val kode_invoice: Int? = null,
    val tglBayar: Int? = null,
    val biaya_tambahan: Int? = null,
    val batas_waktu: Int? = null,
    val pajak: String? = null,
    val qty: String? = null,
    val tgl: String? = null,
    val id_outlet: Int? = null,
    val diskon: String? = null
) : Parcelable
