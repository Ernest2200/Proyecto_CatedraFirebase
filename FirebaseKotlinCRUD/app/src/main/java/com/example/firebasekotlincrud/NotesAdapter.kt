package com.example.firebasekotlincrud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlincrud.databinding.ItemNotesBinding
import com.example.firebasekotlincrud.entities.Notes
import com.example.firebasekotlincrud.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
class NotesAdapter(private val list: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder.binding) {
            tvTitle.text = list[position].name
            tvDate.text = list[position].date
            tvDescription.text = list[position].description
            val repository = NotesRepository.getRepository(holder.binding.root.context)
            tvDelete.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteOne(list[position].id)
                }
            }
        }
    }
    override fun getItemCount(): Int = list.size
}