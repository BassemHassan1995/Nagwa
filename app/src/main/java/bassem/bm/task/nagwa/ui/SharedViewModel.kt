package bassem.bm.task.nagwa.ui

import androidx.databinding.ObservableField
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import bassem.bm.task.nagwa.utils.Result
import javax.inject.Inject

class SharedViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val result: ObservableField<String> = ObservableField("")

    init {
        getItemsList()
    }

    private fun getItemsList() {
        when (val result = repository.getDownloadedItems()) {
            is Result.Failure -> setResult("Error Getting List")
            is Result.Success -> setResult("${result.value.size} Items Retrieved")
        }
    }

    private fun setResult(itemsResult: String) {
        result.set(itemsResult)
    }
}