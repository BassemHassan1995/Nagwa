package bassem.bm.task.nagwa.data.repository

import android.annotation.SuppressLint
import bassem.bm.task.nagwa.data.local.DataItemDao
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.remote.ApiHelper
import bassem.bm.task.nagwa.utils.RESPONSE_JSON
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val moshi: Moshi,
    private val itemDao: DataItemDao
) : Repository {

    @SuppressLint("CheckResult")
    override fun getItemsList(onSuccess: (List<DataItem>) -> Unit) {
        apiHelper.getItemsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, { getCashedItemsList(onSuccess) })
    }

    @SuppressLint("CheckResult")
    private fun getCashedItemsList(onSuccess: (List<DataItem>) -> Unit) {
        itemDao.getAllItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, { getOfflineItemsList(onSuccess) })
    }

    private fun getOfflineItemsList(onSuccess: (List<DataItem>) -> Unit) {
        val listType = Types.newParameterizedType(List::class.java, DataItem::class.java)

        val jsonAdapter: JsonAdapter<List<DataItem>> = moshi.adapter(listType)
        onSuccess(jsonAdapter.fromJson(RESPONSE_JSON) ?: listOf())
    }

    override fun getDownloadedItems(): List<DataItem> = TODO("Not yet implemented")

    @SuppressLint("CheckResult")
    override fun downloadItem(dataItem: DataItem, onProgress: (Int) -> Unit, onComplete: (Unit) -> Unit, onError: (Throwable) -> Unit) {
        fakeDownload(onProgress, onError)
        dataItem.isDownloaded = true
        itemDao.downloadItem(dataItem)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onComplete, onError)
    }

    @SuppressLint("CheckResult")
    private fun fakeDownload(onProgress: (Int) -> Unit, onError: (Throwable) -> Unit) {
        Observable.intervalRange(1, 100, 10, 50, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.toInt() }
            .subscribe(onProgress, onError)
    }

}
