package id.itborneo.laundrymanage.utils

import android.text.TextUtils
import android.util.Patterns

fun String.toRupiah(): String {
    var result = ""
    if (this.length > 2) {
        result = StringBuffer(this).insert(this.length - 3, ".").toString()

    }

    if (this.length > 6) {
        // -6, karena 6 digit. dikurang 1 karena ada titik dari logic diatas (tambah titik di digit 3)
        result = StringBuffer(result).insert(result.length - 6 - 1, ".").toString()
    }

    result = "Rp $result"
    return result
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()