package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import io.reactivex.functions.Consumer

interface Repository {
    fun getItemsList(onSuccess: Consumer<List<ResponseDataItem>>, onError : Consumer<Throwable>)

    fun downloadItem(id: Int) : Result<Boolean>

    fun getDownloadedItems() : Result<List<ResponseDataItem>>
}