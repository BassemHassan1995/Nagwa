package bassem.bm.task.nagwa.data.repository

import android.annotation.SuppressLint
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.remote.ApiHelper
import bassem.bm.task.nagwa.utils.RESPONSE_JSON
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val moshi: Moshi
) : Repository {

    @SuppressLint("CheckResult")
    override fun getItemsList(onSuccess: Consumer<List<DataItem>>, onError: Consumer<Throwable>) {
        apiHelper.getItemsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError)
    }

    @SuppressLint("CheckResult")
    override fun downloadItem(onProgress: Consumer<Int>, onError: Consumer<Throwable>) {
        Observable.intervalRange(1, 100, 10, 50, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { it.toInt() }
            .subscribe(onProgress, onError)
    }

    override fun getDownloadedItems(): List<DataItem> = TODO("Not yet implemented")

    override fun getOfflineItemsList(): List<DataItem> {
        val listType = Types.newParameterizedType(List::class.java, DataItem::class.java)

        val jsonAdapter: JsonAdapter<List<DataItem>> = moshi.adapter(listType)
        return jsonAdapter.fromJson(RESPONSE_JSON) ?: listOf()
    }
}
