package bassem.bm.task.nagwa.data.repository

import android.annotation.SuppressLint
import android.util.Log
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.remote.ApiHelper
import bassem.bm.task.nagwa.utils.RESPONSE_JSON
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiHelper: ApiHelper, private val moshi: Moshi) : Repository {

    @SuppressLint("CheckResult")
    override fun getItemsList(onSuccess: Consumer<List<DataItem>>, onError : Consumer<Throwable>){
        apiHelper.getItemsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError)
    }

    override fun downloadItem(id: Int): Result<Boolean> = TODO("Not yet implemented")

    override fun getDownloadedItems(): Result<List<DataItem>> = TODO("Not yet implemented")

    override fun getOfflineItemsList(): List<DataItem> {
        val listType = Types.newParameterizedType(List::class.java, DataItem::class.java)

        val jsonAdapter: JsonAdapter<List<DataItem>> = moshi.adapter(listType)
        return jsonAdapter.fromJson(RESPONSE_JSON)?: listOf()
    }
}
