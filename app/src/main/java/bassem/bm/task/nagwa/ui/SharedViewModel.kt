package bassem.bm.task.nagwa.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableBoolean
import bassem.bm.task.nagwa.data.model.DOWNLOAD_STATE
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import bassem.bm.task.nagwa.ui.list.DataItemViewModel
import bassem.bm.task.nagwa.utils.toViewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val isDownloadingItem = ObservableBoolean(false)
    private var currentViewModel: DataItemViewModel? = null

    private val _list: MutableStateFlow<List<DataItemViewModel>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<DataItemViewModel>> = _list

    init {
        getItemsList()
    }

    private fun getItemsList() {
        isLoading.set(true)
        repository.getItemsList(this::bindList)
    }

    private fun bindList(list: List<DataItem>) {
        isLoading.set(false)
        _list.value = list.toViewModels()
    }

    @SuppressLint("CheckResult")
    fun downloadItem(dataItemViewModel: DataItemViewModel) {
        if (dataItemViewModel.downloadState.get() == DOWNLOAD_STATE.NOT_DOWNLOADED && !isDownloadingItem.get()) {
            currentViewModel = dataItemViewModel
            isDownloadingItem.set(true)
            dataItemViewModel.downloadState.set(DOWNLOAD_STATE.DOWNLOADING)
            repository.downloadItem(
                dataItemViewModel.item,
                this::updateProgress,
                this::downloadCompleted,
                this::handleError
            )
        }
    }

    private fun updateProgress(progress: Int) {
        with(currentViewModel!!) {
            downloadProgress.set(progress)
            if (progress == 100) {
                downloadState.set(DOWNLOAD_STATE.DOWNLOADED)
                currentViewModel = null
                isDownloadingItem.set(false)
            }
        }
    }

    private fun downloadCompleted() {
        Log.d("TESTING", "downloadCompleted")
    }

}

