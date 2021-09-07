package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("movies")
    fun getItemsList(): Response<List<ResponseDataItem>>

}