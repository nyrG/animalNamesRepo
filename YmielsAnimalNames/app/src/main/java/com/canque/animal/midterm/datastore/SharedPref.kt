package com.canque.animal.midterm.datastore

import android.content.Context
import com.canque.animal.midterm.models.Animal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPref (context: Context){
    private val gson = Gson()

    private val sharedPref = context
        .getSharedPreferences("AnimalData", Context.MODE_PRIVATE)

    fun getAnimalList(): MutableList<Animal> {
        val json = sharedPref.getString("animalList", "")
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(json, object : TypeToken<MutableList<Animal>>() {}.type)
        }
    }

    fun saveAnimalList(animalList: MutableList<Animal>) {
        val editor = sharedPref.edit()
        val json = gson.toJson(animalList)
        editor.putString("animalList", json)
        editor.apply()
    }
}