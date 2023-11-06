package com.canque.animal.midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.canque.animal.midterm.adapters.BlockAdapter
import com.canque.animal.midterm.databinding.ActivityManageBlockBinding
import com.canque.animal.midterm.datastore.SharedPref

class ManageBlockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageBlockBinding
    private lateinit var adapter: BlockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBlockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing SharedPreferences
        val sharedPref = SharedPref(this)
        //Retrieving List of Animals
        var animalList = sharedPref.getAnimalList()
        val blockedAnimalList = animalList.filter { it.isBlocked }.toMutableList()

        // Check if the blockedAnimalList is empty
        if (blockedAnimalList.isEmpty()) {
            // If empty, display the message
            binding.noBlockedAnimalsMessage.visibility = View.VISIBLE
        } else {
            // If not empty, hide the message
            binding.noBlockedAnimalsMessage.visibility = View.GONE
        }

        adapter = BlockAdapter(this, blockedAnimalList) { position ->
            // Implement the logic for unblocking the animal here
            Log.d("Debug", "Unblocking animal at position: $position")
            val unblockedAnimal = blockedAnimalList[position]
            // Assuming that you want to unblock the animal, so set isBlocked to false
            unblockedAnimal.isBlocked = false
            // Update the list and notify the adapter
            blockedAnimalList.removeAt(position)
            adapter.notifyItemRemoved(position)

            // Optionally, save the updated list to SharedPreferences or any other data storage
            sharedPref.saveAnimalList(animalList)
        }

        adapter.notifyDataSetChanged()

        Log.d("Debug", "blocked animalList contents: $animalList")
        Log.d("Debug", "REAL blocked animalList contents: $blockedAnimalList [Size of blockedAnimalList: ${blockedAnimalList.size}]")
        binding.blockedAnimalList.layoutManager = LinearLayoutManager(this)
        binding.blockedAnimalList.adapter = adapter
    }
}