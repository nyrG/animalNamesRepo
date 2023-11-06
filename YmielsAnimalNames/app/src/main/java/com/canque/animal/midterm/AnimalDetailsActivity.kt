package com.canque.animal.midterm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.canque.animal.midterm.constants.Constants
import com.canque.animal.midterm.databinding.ActivityAnimalDetailsBinding
import com.canque.animal.midterm.datastore.SharedPref
import com.canque.animal.midterm.models.Animal

class AnimalDetailsActivity : AppCompatActivity() {
    companion object {
        const val YOUR_REQUEST_CODE = 123
    }

    private lateinit var binding: ActivityAnimalDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initializing SharedPreferences
        val sharedPref = SharedPref(this)
        //Retrieving List of Animals
        var animalList = sharedPref.getAnimalList()
        //Populating List of Animals

        val name = intent.getStringExtra(Constants.PARAM_NAME)
        val details = intent.getStringExtra(Constants.PARAM_DETAILS)

        binding.name.text = name
        binding.details.text = details

        // When the "Block" button is clicked
        binding.blockButton.setOnClickListener {
            if (name != null) {
                blockAnimalAndReturnResult(name, animalList, sharedPref)
            }
            /*// Prepare data to send back to AnimalNamesActivity
            val resultIntent = Intent()
            resultIntent.putExtra(Constants.PARAM_NAME, name)
            resultIntent.putExtra(Constants.PARAM_IS_BLOCKED, true) // Set animal as blocked

            val animalToBlock = animalList.find { it.name == name }
            animalToBlock?.isBlocked = true

            sharedPref.saveAnimalList(animalList)

            // Set the result and finish this activity
            setResult(Activity.RESULT_OK, resultIntent)
            finish()*/
        }
    }
    private fun blockAnimalAndReturnResult(name: String, animalList: MutableList<Animal>, sharedPref: SharedPref) {
        val resultIntent = Intent()
        resultIntent.putExtra(Constants.PARAM_NAME, name)
        resultIntent.putExtra(Constants.PARAM_IS_BLOCKED, true) // Set animal as blocked

        val animalToBlock = animalList.find { it.name == name }
        animalToBlock?.isBlocked = true

        sharedPref.saveAnimalList(animalList)

        // Set the result and finish this activity
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}