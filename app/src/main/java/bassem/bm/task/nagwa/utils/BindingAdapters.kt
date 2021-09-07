package bassem.bm.task.nagwa.utils

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("visibleIf")
fun bindVisibility(view: View, isVisible: Boolean) {
    view.visibility = when(isVisible){
        true -> View.VISIBLE
        false -> View.GONE
    }
}