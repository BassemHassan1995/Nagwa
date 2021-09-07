package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import bassem.bm.task.nagwa.utils.RESPONSE_JSON
import bassem.bm.task.nagwa.utils.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


class RepositoryImpl : Repository {

    override suspend fun getItemsList(): Result<List<ResponseDataItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadItem(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getDownloadedItems(): Result<List<ResponseDataItem>> = Result {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, ResponseDataItem::class.java)

        val jsonAdapter: JsonAdapter<List<ResponseDataItem>> = moshi.adapter(listType)
        jsonAdapter.fromJson(RESPONSE_JSON)!!
    }
}