package bassem.bm.task.nagwa.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bassem.bm.task.nagwa.databinding.DataItemBinding

class DataItemAdapter(private val onItemClicked: (DataItemViewModel) -> Unit) :
    ListAdapter<DataItemViewModel, DataItemAdapter.DataItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataItemViewHolder =
        DataItemViewHolder(
            DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClicked
        )

    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DataItemViewHolder(
        private val binding: DataItemBinding,
        val onItemClicked: (DataItemViewModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItemViewModel: DataItemViewModel) {
            binding.viewModel = dataItemViewModel
            binding.btnDownload.setOnClickListener { onItemClicked(dataItemViewModel) }
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<DataItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: DataItemViewModel,
            newItem: DataItemViewModel
        ): Boolean {
            return oldItem.item.id == newItem.item.id
        }

        override fun areContentsTheSame(
            oldItem: DataItemViewModel,
            newItem: DataItemViewModel
        ): Boolean {
            return oldItem.item.name == newItem.item.name
        }
    }

}