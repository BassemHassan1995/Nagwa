package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import retrofit2.Response

interface ApiHelper {

    fun getItemsList(): Response<List<ResponseDataItem>>

}