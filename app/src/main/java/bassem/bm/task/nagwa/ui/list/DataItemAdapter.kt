package bassem.bm.task.nagwa.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bassem.bm.task.nagwa.data.model.DataItem
import bassem.bm.task.nagwa.databinding.DataItemBinding

class DataItemAdapter : ListAdapter<DataItem, DataItemAdapter.DataItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataItemViewHolder =
        DataItemViewHolder(
            DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DataItemViewHolder(private val binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dataItem: DataItem) {
            binding.item = dataItem
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

}