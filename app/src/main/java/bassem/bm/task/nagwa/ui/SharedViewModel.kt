package bassem.bm.task.nagwa.ui

import androidx.databinding.ObservableBoolean
import bassem.bm.task.nagwa.data.model.DOWNLOAD_STATE
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import bassem.bm.task.nagwa.ui.list.DataItemViewModel
import bassem.bm.task.nagwa.utils.toViewModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val isDownloadingItem = ObservableBoolean(false)
    private var currentViewModel: DataItemViewModel? = null

    private val _list: MutableStateFlow<List<DataItemViewModel>> = MutableStateFlow(emptyList())
    val list: MutableStateFlow<List<DataItemViewModel>> = _list

    private val _downloadList: MutableStateFlow<List<DataItemViewModel>> =
        MutableStateFlow(emptyList())
    val downloadList: MutableStateFlow<List<DataItemViewModel>> = _downloadList

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
        _downloadList.value =
            list.filter { dataItem -> dataItem.isDownloaded }.sortedBy { dataItem -> dataItem.name }
                .toViewModels()
    }

    fun onDownloadButtonClicked(dataItemViewModel: DataItemViewModel) {
        if (!isDownloadingItem.get()) {
            when (dataItemViewModel.item.isDownloaded) {
                true -> removeDownloadedItem(dataItemViewModel)
                false -> downloadItem(dataItemViewModel)
            }
        }
    }

    private fun downloadItem(dataItemViewModel: DataItemViewModel) {
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

    private fun removeDownloadedItem(dataItemViewModel: DataItemViewModel) {
        currentViewModel = dataItemViewModel
        repository.removeDownloadedItem(dataItemViewModel.item, this::removeDownloadedCompleted)
    }

    private fun updateProgress(progress: Int) {
        currentViewModel?.apply {
            downloadProgress.set(progress)
        }
    }

    private fun downloadCompleted() {
        currentViewModel?.apply {
            val currentList: MutableList<DataItemViewModel> = _downloadList.value.toMutableList()
            currentList.add(this)
            _downloadList.value = currentList
            downloadState.set(DOWNLOAD_STATE.DOWNLOADED)
            currentViewModel = null
        }
        isDownloadingItem.set(false)
    }

    private fun removeDownloadedCompleted() {
        currentViewModel?.apply {
            val currentList: MutableList<DataItemViewModel> = _downloadList.value.toMutableList()
            currentList.remove(this)
            _downloadList.value = currentList
            downloadState.set(DOWNLOAD_STATE.NOT_DOWNLOADED)
            currentViewModel = null
        }
        isDownloadingItem.set(false)
    }
}

