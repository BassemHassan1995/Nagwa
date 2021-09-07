package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("movies")
    fun getItemsList(): Single<List<DataItem>>

}