package bassem.bm.task.nagwa.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){

    val isLoading = ObservableBoolean(false)

    protected open fun handleError(error: Throwable){
        isLoading.set(false)
    }

}