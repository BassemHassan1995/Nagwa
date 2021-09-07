package bassem.bm.task.nagwa.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import bassem.bm.task.nagwa.R
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

