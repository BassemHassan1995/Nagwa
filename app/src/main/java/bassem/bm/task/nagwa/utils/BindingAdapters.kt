package bassem.bm.task.nagwa.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import bassem.bm.task.nagwa.R
import bassem.bm.task.nagwa.data.model.DOWNLOAD_STATE
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.model.TYPE
import bassem.bm.task.nagwa.ui.list.DataItemAdapter


@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<DataItem>?
) {
    val adapter = recyclerView.adapter as DataItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("visibleIf")
fun bindVisibility(view: View, isVisible: Boolean) {
    view.visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

@BindingAdapter("itemType")
fun bindItemType(view: ImageView, type: TYPE) = view.setImageResource(
    when (type) {
        TYPE.VIDEO -> R.drawable.ic_video
        TYPE.PDF -> R.drawable.ic_file
    }
)

@BindingAdapter("downloadState")
fun bindDownloadState(view: ImageView, state: DOWNLOAD_STATE) = view.setImageResource(
    when (state) {
        DOWNLOAD_STATE.NOT_DOWNLOADED -> R.drawable.ic_download
        DOWNLOAD_STATE.DOWNLOADING -> R.drawable.ic_downloading
        DOWNLOAD_STATE.DOWNLOADED -> R.drawable.ic_downloaded
    }
)

@BindingAdapter("downloadState")
fun bindDownloadState(progress: ProgressBar, state: DOWNLOAD_STATE) {
    progress.visibility =
        when (state) {
            DOWNLOAD_STATE.NOT_DOWNLOADED -> View.GONE
            DOWNLOAD_STATE.DOWNLOADING -> View.GONE
            DOWNLOAD_STATE.DOWNLOADED -> View.GONE
        }
}

