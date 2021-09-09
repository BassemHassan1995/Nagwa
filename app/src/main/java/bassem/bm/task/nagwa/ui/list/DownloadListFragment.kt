package bassem.bm.task.nagwa.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import bassem.bm.task.nagwa.databinding.FragmentDownloadListBinding
import bassem.bm.task.nagwa.ui.SharedViewModel
import bassem.bm.task.nagwa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DownloadListFragment : BaseFragment<FragmentDownloadListBinding>() {

    override val viewModel by activityViewModels<SharedViewModel>()
    private val itemAdapter = DataItemAdapter { viewModel.onDownloadButtonClicked(it) }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDownloadListBinding = FragmentDownloadListBinding.inflate(layoutInflater, container, false)

    override fun setUpViews() {
        with(binding.recyclerView)
        {
            adapter = itemAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

    }
}