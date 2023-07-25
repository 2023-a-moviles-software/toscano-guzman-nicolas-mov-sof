package com.example.deber2recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber2recyclerview.entidad.Categoria
import com.example.deber2recyclerview.databinding.ActivityMainBinding
import com.example.deber2recyclerview.databinding.ItemCategoriaComidasBinding

class CategoriaAdapter(
    private val listaCategoria: List<Categoria>,
    private val activityBinding: ActivityMainBinding
) : RecyclerView.Adapter<CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriaComidasBinding.inflate(inflater, parent, false)
        return CategoriaViewHolder(binding, activityBinding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = listaCategoria[position]
        holder.constructor(categoria)
    }

    override fun getItemCount(): Int {
        return listaCategoria.size
    }
}