package com.example.firebasekotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log

import com.example.firebasekotlincrud.databinding.ActivityEditBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = intent.getStringExtra("key")
        val database = Firebase.database
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val myRef = database.getReference("places").child(key)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val places:PlacesTuristic? = dataSnapshot.getValue(PlacesTuristic::class.java)
                if (places != null) {
                   binding.nameEditText.text = Editable.Factory.getInstance().newEditable(places.name)
                    binding.dateEditText.text = Editable.Factory.getInstance().newEditable(places.location)
                    binding.descriptionEditText.text = Editable.Factory.getInstance().newEditable(places.description)
                    binding.urlEditText.text = Editable.Factory.getInstance().newEditable(places.url)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        binding.saveButton.setOnClickListener {
            val name : String = binding.nameEditText.text.toString()
            val location : String = binding.dateEditText.text.toString()
            val description: String = binding.descriptionEditText.text.toString()
            val url: String = binding.urlEditText.text.toString()
            with(binding) {
                if (name.isNullOrBlank() || location.isNullOrBlank() || description.isNullOrBlank() || url.isNullOrBlank()) {
                    Snackbar.make(this.root, "Algunos campos estan vacios", Snackbar.LENGTH_SHORT).show()
                } else {


                    myRef.child("name").setValue(name)
                    myRef.child("location").setValue(location)
                    myRef.child("description").setValue(description)
                    myRef.child("url").setValue(url)

                    finish()

                }                }

        }
    }

}