package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseData
import bassem.bm.task.nagwa.utils.Result

interface Repository {
    suspend fun getItemsList(): Result<ResponseData>

    suspend fun downloadItem(id: Int) : Result<Boolean>

    suspend fun getDownloadedItems() : Result<ResponseData>
}