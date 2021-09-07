package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import bassem.bm.task.nagwa.utils.Result

interface Repository {
    suspend fun getItemsList(): Result<List<ResponseDataItem>>

    suspend fun downloadItem(id: Int) : Result<Boolean>

    fun getDownloadedItems() : Result<List<ResponseDataItem>>
}