package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.DataItem
import io.reactivex.functions.Consumer

interface Repository {
    fun getItemsList(onSuccess: Consumer<List<DataItem>>, onError : Consumer<Throwable>)

    fun downloadItem(onProgress: Consumer<Int>, onError: Consumer<Throwable>)

    fun getDownloadedItems() : List<DataItem>

    fun getOfflineItemsList() : List<DataItem>
}