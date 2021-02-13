package id.itborneo.laundrymanage.transaction

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionModel(
    val id: String? = null,
    val customer_name: String?,
    val progress_status: String?,
    val totalPrice: String?,
    val payment_status: String?,
    val packetId: String?
) : Parcelable
