package com.example.deber2recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber2recyclerview.databinding.ItemCategoriaBinding
import com.example.deber2recyclerview.entidad.Categoria

class CategoriaAdapter(private val listaCategorias: List<Categoria>) :
    RecyclerView.Adapter<CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val binding =
            ItemCategoriaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = listaCategorias[position]
        holder.constructor(categoria)
    }

    override fun getItemCount(): Int {
        return listaCategorias.size
    }
}

