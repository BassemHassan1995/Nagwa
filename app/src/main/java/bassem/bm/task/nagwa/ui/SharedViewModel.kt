package bassem.bm.task.nagwa.ui

import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.data.repository.Repository
import bassem.bm.task.nagwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _list: MutableStateFlow<List<DataItem>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<DataItem>> = _list

    init {
        getItemsList()
    }

    private fun getItemsList() {
        isLoading.set(true)
        repository.getItemsList(this::bindList, this::handleError)
    }

    private fun bindList(list: List<DataItem>) {
        isLoading.set(false)
        _list.value = list
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        _list.value = repository.getOfflineItemsList()      //Get list from JSON
    }

}