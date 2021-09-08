package bassem.bm.task.nagwa.data.model

import com.squareup.moshi.JsonClass

enum class TYPE {
    VIDEO, PDF
}

enum class DOWNLOAD_STATE {
    NOT_DOWNLOADED, DOWNLOADING, DOWNLOADED
}

@JsonClass(generateAdapter = true)
data class DataItem(
    val id: Int = 0,
    val name: String = "",
    val type: TYPE = TYPE.VIDEO,
    val url: String = "",
)