package id.itborneo.laundrymanage.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersModel(
    val password: String? = null,
    val role: String? = null,
    val nama: String? = null,
    val idOutlet: String? = null,
    val id: String? = null,
    val email: String? = null
): Parcelable
