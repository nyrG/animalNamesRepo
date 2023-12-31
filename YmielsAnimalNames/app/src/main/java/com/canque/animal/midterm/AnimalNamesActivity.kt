package com.canque.animal.midterm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.canque.animal.midterm.AnimalDetailsActivity.Companion.YOUR_REQUEST_CODE
import com.canque.animal.midterm.adapters.AnimalAdapter
import com.canque.animal.midterm.constants.Constants
import com.canque.animal.midterm.databinding.ActivityAnimalNamesBinding
import com.canque.animal.midterm.datastore.SharedPref
import com.canque.animal.midterm.models.Animal

class AnimalNamesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimalNamesBinding
    private lateinit var animalList: MutableList<Animal>
    private lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalNamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing SharedPreferences
        val sharedPref = SharedPref(this)
        //Retrieving List of Animals
        animalList = sharedPref.getAnimalList()
        //Populating List of Animals

        if (animalList.isEmpty()) {
            // Initialize the list with default animals for first run
            val defaultAnimals = mutableListOf(
                Animal("Antelope", "A deer with cool horns."),
                Animal("Bat", "Flies at night."),
                Animal("Cat", "Likes catnip."),
                Animal("Dog", "Man's best friend."),
                Animal("Elephant", "The largest land animal."),
                Animal("Fox", "Cunning and sly."),
                Animal("Giraffe", "Has a long neck."),
                Animal("Horse", "Used for transportation."),
                Animal("Iguana", "A reptile with scales."),
                Animal("Jaguar", "A spotted big cat."),
                Animal("Kangaroo", "Hops around."),
                Animal("Lion", "The king of the jungle."),
                Animal("Monkey", "Swings from trees."),
                Animal("Nightingale", "Sings beautifully."),
                Animal("Ostrich", "Tall and flightless."),
                Animal("Panda", "Eats bamboo."),
                Animal("Quokka", "Smiling marsupial."),
                Animal("Raccoon", "Loves trash cans."),
                Animal("Squirrel", "Collects nuts."),
                Animal("Tiger", "Striped and fierce."),
                Animal("Uakari", "Bright red face."),
                Animal("Vulture", "Scavenger bird."),
                Animal("Walrus", "Tusked marine mammal."),
                Animal("X-ray Tetra", "Small fish."),
                Animal("Yak", "Himalayan livestock."),
                Animal("Zebra", "Striped black and white.")
            )
            animalList.addAll(defaultAnimals)

            // Save the initial list to SharedPreferences
            sharedPref.saveAnimalList(animalList)
        }

        //Filters Unblocked Animals
        val unblockedAnimalList = animalList.filter { !it.isBlocked }.toMutableList()
        //Sorts Alphabetically
        unblockedAnimalList.sortBy { it.name }

        adapter = AnimalAdapter(this, unblockedAnimalList)

        Log.d("Debug", "animalList contents: $animalList")
        binding.animalList.layoutManager = LinearLayoutManager(this)
        binding.animalList.adapter = adapter

        binding.blockButton.setOnClickListener {
            // Create an Intent to launch the ManageBlockActivity
            val intent = Intent(this, ManageBlockActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == YOUR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //adapter.notifyDataSetChanged()
            // Retrieve data from the result intent
            val name = data?.getStringExtra(Constants.PARAM_NAME)
            val isBlocked = data?.getBooleanExtra(Constants.PARAM_IS_BLOCKED, false)

            // Update your animal list based on the received data
            if (name != null && isBlocked == true) {
                val animalToBlock = animalList.find { it.name == name }
                animalToBlock?.isBlocked = true

                // Update the RecyclerView to reflect the changes
                // YourAdapter.notifyDataSetChanged() or similar method
            }
        }
        Log.d("Debug", "Updated animalList: $animalList")
    }
}