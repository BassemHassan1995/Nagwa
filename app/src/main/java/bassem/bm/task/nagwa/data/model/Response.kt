package bassem.bm.task.nagwa.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

enum class TYPE {
    VIDEO, PDF
}

enum class DOWNLOAD_STATE {
    NOT_DOWNLOADED, DOWNLOADING, DOWNLOADED
}

@Entity
@JsonClass(generateAdapter = true)
data class DataItem(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val type: TYPE = TYPE.VIDEO,
    val url: String = "",
    var isDownloaded: Boolean = false
)