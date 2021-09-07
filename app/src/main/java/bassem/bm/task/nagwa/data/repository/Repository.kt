package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.functions.Consumer

interface Repository {
    fun getItemsList(onSuccess: Consumer<List<DataItem>>, onError : Consumer<Throwable>)

    fun downloadItem(id: Int) : Result<Boolean>

    fun getDownloadedItems() : Result<List<DataItem>>

    fun getOfflineItemsList() : List<DataItem>
}