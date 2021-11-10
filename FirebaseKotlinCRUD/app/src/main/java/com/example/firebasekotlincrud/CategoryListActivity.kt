package com.example.firebasekotlincrud


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivityCategoryListBinding


class CategoryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categorylist.setOnClickListener {


            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("datos", 1);
            it.context.startActivity(intent)
        }


        binding.categorylist2.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("datos", 2);
            it.context.startActivity(intent)
        }

        binding.categorylist3.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("datos", 3);
            it.context.startActivity(intent)
        }

        binding.categorylist4.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("datos", 4);
            it.context.startActivity(intent)
        }
        binding.imageView.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            it.context.startActivity(intent)
        }
    }

}