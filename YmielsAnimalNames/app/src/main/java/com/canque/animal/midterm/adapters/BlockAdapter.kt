package com.canque.animal.midterm.adapters

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canque.animal.midterm.databinding.BlockedItemBinding
import com.canque.animal.midterm.models.Animal

class BlockAdapter(
    private val activity: Activity,
    private val animals: MutableList<Animal>,
    private val onItemClick: (Int) -> Unit,
): RecyclerView.Adapter<BlockAdapter.BlockViewHolder>() {

    class BlockViewHolder(
        private val activity: Activity,
        private val binding: BlockedItemBinding,
        private val onItemClick: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            // Set an OnClickListener for the "Unblock" button
            binding.unblockText.setOnClickListener {
                val position = adapterPosition
                Log.d("Debug", "Unblock clicked at position: $position")
                onItemClick(position)
            }
        }
        fun bind(animal: Animal) {
            binding.name.text = animal.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BlockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BlockedItemBinding.inflate(
            inflater,
            parent,
            false,
        )
        // Define the onItemClick lambda here
        val onItemClick: (Int) -> Unit = { position ->
            // Implement the logic for unblocking the animal here
            val blockedAnimal = animals[position]
            // You can remove the animal from the list or perform other actions
            // based on the position (index) of the clicked item
        }
        return BlockViewHolder(activity, binding, onItemClick)
    }

    override fun getItemCount() = animals.size

    override fun onBindViewHolder(
        holder: BlockViewHolder,
        position: Int,
    ) {
        holder.bind(animals[position])
    }
}
