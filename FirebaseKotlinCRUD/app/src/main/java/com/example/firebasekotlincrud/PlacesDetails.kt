package com.example.firebasekotlincrud

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.databinding.ActivityPlacesDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class PlacesDetails : AppCompatActivity() {
    private lateinit var binding : ActivityPlacesDetailsBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val key = intent.getStringExtra("key")

        val database = Firebase.database
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val myRef = database.getReference("places").child(key)



        binding.FavoriteActionButton.setOnClickListener{

    val favorite = true
    myRef.child("favorite").setValue(favorite)
            Toast.makeText(applicationContext, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
    finish()

        }
binding.FavoriteActionButton.setOnLongClickListener {

    val favorite = false
    myRef.child("favorite").setValue(favorite)
    Toast.makeText(applicationContext, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
    finish()
    true
}

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val places: PlacesTuristic? = dataSnapshot.getValue(PlacesTuristic::class.java)
                if (places != null) {
                    binding.nameTextView.text = places.name.toString()
                    binding.descriptionTextView.text = places.description.toString()
                    binding.typeTextView.text = "Categoría: "+places.type.toString()
                    images(places.url.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }

    private  fun images(url: String){
        Glide.with(applicationContext)
            .load(url)
            .into(binding.posterImgeView)




    }
}


