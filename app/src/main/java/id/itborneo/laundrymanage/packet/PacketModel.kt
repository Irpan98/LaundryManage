package id.itborneo.laundrymanage.packet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PacketModel(
    val id: Int? = null,
    val name: String?,
    val price: String?,
    val note: String?
) : Parcelable
