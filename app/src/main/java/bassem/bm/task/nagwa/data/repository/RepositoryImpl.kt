package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.ResponseDataItem
import bassem.bm.task.nagwa.data.remote.ApiHelper
import bassem.bm.task.nagwa.utils.RESPONSE_JSON
import bassem.bm.task.nagwa.utils.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.Exception
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) : Repository {

    override fun getItemsList(): Result<List<ResponseDataItem>> = Result {
        val response = apiHelper.getItemsList()
        when (response.isSuccessful) {
            true -> response.body()!!
            false -> throw Exception(response.errorBody().toString())
        }
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