package bassem.bm.task.nagwa.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import bassem.bm.task.nagwa.databinding.FragmentListBinding
import bassem.bm.task.nagwa.ui.SharedViewModel
import bassem.bm.task.nagwa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    override val viewModel by activityViewModels<SharedViewModel>()
    val itemAdapter = DataItemAdapter()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListBinding = FragmentListBinding.inflate(layoutInflater, container, false)

    override fun setUpViews() {
        with(binding.recyclerView)
        {
            adapter = itemAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

    }
}