package com.example.firebasekotlincrud


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivityAddBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    private val database = Firebase.database
    private val options = arrayListOf("Playas", "Lagos","Centros Comerciales","Centros Turísticos")

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myRef = database.getReference("places")

        val name=binding.nameEditText.text
        val location=binding.dateEditText.text
        val description=binding.descriptionEditText.text
        val url=binding.urlEditText.text
        val favorite = false



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




        binding.saveButton.setOnClickListener {
            with(binding) {
                if (name.isNullOrBlank() || location.isNullOrBlank() || description.isNullOrBlank() || url.isNullOrBlank()) {
                    Snackbar.make(this.root, "Algunos campos estan vacios", Snackbar.LENGTH_SHORT).show()
                } else {

                    val places = PlacesTuristic(name.toString().lowercase(), location.toString(), description.toString(), favorite, url.toString(), type)
                    myRef.child(myRef.push().key.toString()).setValue(places)

                    finish()

                }
            }
        }
    }


}