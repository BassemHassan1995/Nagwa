package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import io.reactivex.Single

interface ApiHelper {

    fun getItemsList(): Single<List<ResponseDataItem>>

}