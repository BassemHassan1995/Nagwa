package bassem.bm.task.nagwa.data.repository

import bassem.bm.task.nagwa.data.model.DataItem

interface Repository {
    fun getItemsList(onSuccess: (List<DataItem>) -> Unit)

    fun downloadItem(
        dataItem: DataItem,
        onProgress: (Int) -> Unit,
        onComplete: (Unit) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun getDownloadedItems(): List<DataItem>
}