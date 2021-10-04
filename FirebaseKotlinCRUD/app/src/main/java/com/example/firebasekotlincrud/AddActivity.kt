package com.example.firebasekotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.databinding.ActivityAddBinding
import com.example.firebasekotlincrud.databinding.ActivityMainBinding

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    private val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myRef = database.getReference("places")

        val name=binding.nameEditText.text
        val date=binding.dateEditText.text
        val description=binding.descriptionEditText.text
        val url=binding.urlEditText.text

        binding.saveButton.setOnClickListener {

            val places = PlacesTuristic(name.toString(), date.toString(), description.toString(), url.toString())
            myRef.child(myRef.push().key.toString()).setValue(places)
            finish()
        }
    }
}