package bassem.bm.task.nagwa.data.model

import com.squareup.moshi.JsonClass

class ResponseData : ArrayList<ResponseData.ResponseDataItem>(){

    @JsonClass(generateAdapter = true)
    data class ResponseDataItem(
        val id: Int = 0,
        val name: String = "",
        val type: String = "",
        val url: String = ""
    )
}