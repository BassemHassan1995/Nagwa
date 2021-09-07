package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseData
import bassem.bm.task.nagwa.utils.Result

class RepositoryImpl : Repository {

    override suspend fun getItemsList(): Result<ResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadItem(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getDownloadedItems(): Result<ResponseData> {
        TODO("Not yet implemented")
    }
}