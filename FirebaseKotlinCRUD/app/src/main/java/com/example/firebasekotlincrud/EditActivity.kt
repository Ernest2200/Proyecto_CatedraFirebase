package com.example.firebasekotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.firebasekotlincrud.databinding.ActivityEditBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding
    private val options = arrayListOf("Playas", "Lagos","Centros Comerciales","Centros Turísticos")
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val spinner = findViewById<Spinner>(R.id.spinnerkotlin)

        var type = ""

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    Snackbar.make(binding.root, "" + id, Snackbar.LENGTH_SHORT).show()
                    val id = spinner.selectedItemId.toInt()
                    when(id){
                        0 -> type = "Playas"

                        1 -> type = "Lagos"

                        2 -> type = "Centros Comerciales"

                        3 -> type = "Centros Turísticos"

                        else -> error("selecicona")

                    }

                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }







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
                    binding.spinnerkotlin
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


                    myRef.child("name").setValue(name.lowercase())
                    myRef.child("location").setValue(location)
                    myRef.child("description").setValue(description)
                    myRef.child("url").setValue(url)
                    myRef.child("type").setValue(type)

                    finish()

                }                }

        }
    }

}