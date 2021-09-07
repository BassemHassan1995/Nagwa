package bassem.bm.task.nagwa.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import bassem.bm.task.nagwa.BR

abstract class BaseFragment<DataBinding : ViewDataBinding> :
    Fragment() {

    protected abstract val viewModel: BaseViewModel

    protected lateinit var binding: DataBinding
    protected abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    private fun init(inflater: LayoutInflater, container: ViewGroup?) {
        binding = getViewBinding(inflater, container).apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@BaseFragment
        }
    }

    open fun setUpViews() {}

    @CallSuper
    open fun observeData() {
    }

}