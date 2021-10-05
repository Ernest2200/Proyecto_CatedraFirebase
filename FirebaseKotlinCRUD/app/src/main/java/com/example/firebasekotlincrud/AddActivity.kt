package com.example.firebasekotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.databinding.ActivityAddBinding

import com.google.android.material.snackbar.Snackbar

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
        val location=binding.dateEditText.text
        val description=binding.descriptionEditText.text
        val url=binding.urlEditText.text

        binding.saveButton.setOnClickListener {
            with(binding) {
                if (name.isNullOrBlank() || location.isNullOrBlank() || description.isNullOrBlank() || url.isNullOrBlank()) {
                    Snackbar.make(this.root, "Algunos campos estan vacios", Snackbar.LENGTH_SHORT).show()
                } else {
                    val places = PlacesTuristic(name.toString(), location.toString(), description.toString(), url.toString())
                    myRef.child(myRef.push().key.toString()).setValue(places)
                    finish()

                }
            }
        }
    }
}