package com.example.deber2recyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber2recyclerview.Comida
import com.example.deber2recyclerview.R
import com.example.deber2recyclerview.databinding.ItemComidaBinding

class ComidaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var totalLikes = 0
    var numeroLikes = 0
    val binding = ItemComidaBinding.bind(view)


//    val nombreComida = view.findViewById<TextView>(R.id.tv_nombre_comida)
//    val precioComida = view.findViewById<TextView>(R.id.tv_precio_comida)
//    val imagen = view.findViewById<ImageView>(R.id.iv_comida)

    fun constructor(comida: Comida) {
        binding.tvNombreComida.text = comida.nombreComida
        binding.tvPrecioComida.text = comida.precio.toString()

        Glide.with(binding.ivComida.context).load(comida.imagen).into(binding.ivComida)
        binding.btnMenos.setOnClickListener {
            quitarLike()
            Toast.makeText(
                binding.ivComida.context,
                "Quitaste un(a)" + comida.nombreComida,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnMas.setOnClickListener {
            anadirLike()
            Toast.makeText(
                binding.ivComida.context,
                "Añadiste un(a)" + comida.nombreComida,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun anadirLike(){
        numeroLikes = numeroLikes + 1
        binding.tvTotalLikes.text = numeroLikes.toString()
        aumentarTotalLikes()
    }

    fun quitarLike(){
        numeroLikes = numeroLikes - 1
        binding.tvTotalLikes.text = numeroLikes.toString()
        disminuirTotalLikes()
    }

    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val totalLikesTextView = binding.tvTotalLikes
        totalLikesTextView.text = totalLikes.toString()
    }

    fun disminuirTotalLikes(){
        totalLikes = totalLikes - 1
        val totalLikesTextView = binding.tvTotalLikes
        totalLikesTextView.text = totalLikes.toString()
    }

}