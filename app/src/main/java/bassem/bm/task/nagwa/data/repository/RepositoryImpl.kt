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

    override fun getItemsList(onSuccess: (List<DataItem>) -> Unit) = getApiItemsList(onSuccess)

    @SuppressLint("CheckResult")
    private fun getApiItemsList(onSuccess: (List<DataItem>) -> Unit) {
        apiHelper.getItemsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                //OnSuccess: save the list to database and return the saved list
                { cacheItemsList(it) { getCachedItemsList(onSuccess) } },
                //OnError: return the cached list from database
                { getCachedItemsList(onSuccess) })
    }

    @SuppressLint("CheckResult")
    private fun getCachedItemsList(onSuccess: (List<DataItem>) -> Unit) {
        itemDao.getAllItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                //OnSuccess: if the list is empty return the offline list from Json, else return the list
                { if (it.isEmpty()) getOfflineItemsList(onSuccess) else onSuccess(it) },
                //OnError: return the offline list from Json
                { getOfflineItemsList(onSuccess) })
    }

    private fun getOfflineItemsList(onSuccess: (List<DataItem>) -> Unit) {
        val listType = Types.newParameterizedType(List::class.java, DataItem::class.java)

        val jsonAdapter: JsonAdapter<List<DataItem>> = moshi.adapter(listType)
        //save the parsed list to database and return the saved list
        cacheItemsList(jsonAdapter.fromJson(RESPONSE_JSON) ?: listOf()) {
            getCachedItemsList(onSuccess)
        }
    }

    @SuppressLint("CheckResult")
    private fun cacheItemsList(list: List<DataItem>, onComplete: () -> Unit) {
        //Save the list to the database
        itemDao.insertAll(list)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onComplete)
    }


    override fun downloadItem(
        dataItem: DataItem,
        onProgress: (Int) -> Unit,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        updateItem(true, dataItem) { fakeDownload(onProgress, onComplete, onError) }
    }

    override fun removeDownloadedItem(dataItem: DataItem, onComplete: () -> Unit) {
        updateItem(false, dataItem, onComplete)
    }

    @SuppressLint("CheckResult")
    private fun updateItem(markDownloaded: Boolean, dataItem: DataItem, onComplete: () -> Unit) {
        dataItem.isDownloaded = markDownloaded
        itemDao.updateItem(dataItem)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { onComplete() }
    }


    @SuppressLint("CheckResult")
    private fun fakeDownload(
        onProgress: (Int) -> Unit,
        onComplete: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Observable.intervalRange(1, 100, 10, 50, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.toInt() }
            .subscribe(onProgress, onError, onComplete)
    }

}
