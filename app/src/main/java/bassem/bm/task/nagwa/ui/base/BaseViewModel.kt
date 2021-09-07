package bassem.bm.task.nagwa.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){

    protected open fun handleError(error: Throwable){
        TODO()
    }
}