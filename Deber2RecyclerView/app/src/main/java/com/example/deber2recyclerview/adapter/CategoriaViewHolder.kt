package com.example.deber2recyclerview.adapter

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber2recyclerview.VistaComida
import com.example.deber2recyclerview.databinding.ItemCategoriaBinding
import com.example.deber2recyclerview.entidad.Categoria

class CategoriaViewHolder(
    private val binding: ItemCategoriaBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun constructor(categoria: Categoria) {
        binding.tvNombreCategoria.text = categoria.nombreCategoria
        Glide.with(binding.ivCategoria.context).load(categoria.imagenCategoria)
            .into(binding.ivCategoria)

        binding.root.setOnClickListener {
            // Crear el Intent y pasar los datos necesarios
            val intent = Intent(binding.root.context, VistaComida::class.java)
            intent.putExtra("Categoria_nombre", categoria.nombreCategoria)
            // Agrega más datos si es necesario
            binding.root.context.startActivity(intent)
        }

        binding.ivCategoria.setOnClickListener {
            Toast.makeText(
                binding.ivCategoria.context,
                "Seleccionaste: " + categoria.nombreCategoria,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}



