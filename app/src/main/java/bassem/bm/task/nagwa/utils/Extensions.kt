package bassem.bm.task.nagwa.utils

import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.ui.list.DataItemViewModel


fun List<DataItem>.toViewModels(): List<DataItemViewModel> =
    map { dataItem -> DataItemViewModel(dataItem) }
