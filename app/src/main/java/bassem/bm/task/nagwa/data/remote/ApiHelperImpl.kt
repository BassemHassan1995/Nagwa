package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.Single
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getItemsList(): Single<List<DataItem>> =
        apiService.getItemsList()
}