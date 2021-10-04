package com.example.firebasekotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.databinding.ActivityFavoritesBinding


class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoritesBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}