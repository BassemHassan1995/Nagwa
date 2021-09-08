package bassem.bm.task.nagwa.ui.list

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import bassem.bm.task.nagwa.data.model.DOWNLOAD_STATE
import bassem.bm.task.nagwa.data.model.DataItem

data class DataItemViewModel(val item: DataItem) {

    val downloadState = ObservableField(DOWNLOAD_STATE.NOT_DOWNLOADED)
    val downloadProgress = ObservableInt(0)

}