package com.canque.animal.midterm.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canque.animal.midterm.AnimalDetailsActivity
import com.canque.animal.midterm.constants.Constants
import com.canque.animal.midterm.models.Animal
import com.canque.animal.midterm.databinding.ItemBinding

class AnimalAdapter(
    private val activity: Activity,
    private val animals: MutableList<Animal>,
): RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(
        private val activity: Activity,
        private val binding: ItemBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            binding.name.text = animal.name
            binding.row.setOnClickListener {

                // Declaring Intent.
                val intent = Intent(
                    activity, // Context of the Current Activity.
                    AnimalDetailsActivity::class.java // Activity that we want to open.
                )

                // Adding String type parameter from current Activity to ProfileActivity.
                intent.putExtra(Constants.PARAM_NAME, animal.name)
                intent.putExtra(Constants.PARAM_DETAILS, animal.details)

                // Open the ProfileActivity.
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(
            inflater,
            parent,
            false,
        )
        return AnimalViewHolder(activity, binding)
    }

    override fun getItemCount() = animals.size

    override fun onBindViewHolder(
        holder: AnimalViewHolder,
        position: Int,
    ) {
        holder.bind(animals[position])
    }
}