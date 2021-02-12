package id.itborneo.laundrymanage.outlite

data class OutliteResponse(
    var `data`: List<Outlite>?,
    var isSuccess: Boolean?,
    var message: String?
)