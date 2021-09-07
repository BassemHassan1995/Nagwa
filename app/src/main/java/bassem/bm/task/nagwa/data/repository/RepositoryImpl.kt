package bassem.bm.task.nagwa.data.repository

import android.annotation.SuppressLint
import android.util.Log
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.remote.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) : Repository {

    @SuppressLint("CheckResult")
    override fun getItemsList(onSuccess: Consumer<List<DataItem>>, onError : Consumer<Throwable>){
        apiHelper.getItemsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(onSuccess, onError)
    }
    private fun handleResponse(list: List<DataItem>){
        Log.d("TESTING", "handleResponse: ${list.size}")
    }

    private fun  handleError(error: Throwable){
        Log.d("TESTING", "handleResponse: ${error.message}")
    }

    override fun downloadItem(id: Int): Result<Boolean> = TODO("Not yet implemented")

    override fun getDownloadedItems(): Result<List<DataItem>> = TODO("Not yet implemented")
}
