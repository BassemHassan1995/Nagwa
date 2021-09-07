package bassem.bm.task.nagwa.ui

import androidx.databinding.ObservableField
import bassem.bm.task.nagwa.data.repository.RepositoryImpl
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import bassem.bm.task.nagwa.utils.Result

class SharedViewModel : BaseViewModel() {

    val result: ObservableField<String> = ObservableField("")

    val repository = RepositoryImpl()

    init {
        getItemsList()
    }

    private fun getItemsList() {
        when(val result = repository.getDownloadedItems()){
            is Result.Failure -> setResult("Error Getting List")
            is Result.Success -> setResult("${result.value.size} Items Retrieved")
        }
    }

    private fun setResult(itemsResult: String){
        result.set(itemsResult)
    }
}