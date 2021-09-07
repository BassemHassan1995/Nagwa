package bassem.bm.task.nagwa.ui

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import bassem.bm.task.nagwa.data.model.ResponseDataItem
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val result: ObservableField<String> = ObservableField("")
    val isLoading = ObservableBoolean(false)

    init {
        getItemsList()
    }

    private fun getItemsList() {
        isLoading.set(true)
        repository.getItemsList(this::bindList, this::handleError)
    }

    private fun bindList(list: List<ResponseDataItem>){
        isLoading.set(false)
        setResult("${list.size} Items Retrieved")
    }

    override fun handleError(error: Throwable){
        isLoading.set(false)
        setResult(error.localizedMessage)
    }

    private fun setResult(itemsResult: String) {
        result.set(itemsResult)
    }
}