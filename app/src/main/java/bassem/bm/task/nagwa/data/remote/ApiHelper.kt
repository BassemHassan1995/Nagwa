package bassem.bm.task.nagwa.data.remote

import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.Single

interface ApiHelper {

    fun getItemsList(): Single<List<DataItem>>

}