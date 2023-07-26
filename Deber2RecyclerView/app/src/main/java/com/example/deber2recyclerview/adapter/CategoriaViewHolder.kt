package com.example.deber2recyclerview.adapter

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber2recyclerview.RV_Alcohol
import com.example.deber2recyclerview.RV_Farmacia
import com.example.deber2recyclerview.RV_Hamburguesa
import com.example.deber2recyclerview.RV_Mascota
import com.example.deber2recyclerview.RV_Pizza
import com.example.deber2recyclerview.entidad.Categoria
import com.example.deber2recyclerview.entidad.Comida
import com.example.deber2recyclerview.databinding.ActivityMainBinding
import com.example.deber2recyclerview.databinding.ItemCategoriaComidasBinding
import com.example.deber2recyclerview.provider.AlcoholProvider
import com.example.deber2recyclerview.provider.FarmaciaProvider
import com.example.deber2recyclerview.provider.HambuguesaProvider
import com.example.deber2recyclerview.provider.MascotaProvider
import com.example.deber2recyclerview.provider.PizzaProvider


class CategoriaViewHolder(
    private val binding: ItemCategoriaComidasBinding,
    private val activityBinding: ActivityMainBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun constructor(categoria: Categoria) {
        binding.tvNombreCategoria.text = categoria.nombreCategoria
        Glide.with(binding.ivCategoria.context).load(categoria.imagenCategoria)
            .into(binding.ivCategoria)

        binding.root.setOnClickListener {
            // Crear el Intent y pasar los datos necesarios
            val intent = Intent(binding.root.context, Comida::class.java)
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

            if (categoria.nombreCategoria == "Pizza") {
//                ComidaAdapter(PizzaProvider.listaComidas)
                irActividad(RV_Pizza::class.java, PizzaProvider.listaComidas)

            } else if (categoria.nombreCategoria == "Alcohol") {
//                activityBinding.recyclerViewComida.adapter =
//                    ComidaAdapter(AlcoholProvider.listaComidas)
                irActividad(RV_Alcohol::class.java, AlcoholProvider.listaComidas)
            } else if (categoria.nombreCategoria == "Farmacia") {
//                activityBinding.recyclerViewComida.adapter =
//                    ComidaAdapter(FarmaciaProvider.listaComidas)
                irActividad(RV_Farmacia::class.java, FarmaciaProvider.listaComidas)
            } else if (categoria.nombreCategoria == "Hamburguesas") {
//                activityBinding.recyclerViewComida.adapter =
//                    ComidaAdapter(HambuguesaProvider.listaComidas)
                irActividad(RV_Hamburguesa::class.java, HambuguesaProvider.listaComidas)
            } else if (categoria.nombreCategoria == "Mascotas") {
//                activityBinding.recyclerViewComida.adapter =
//                    ComidaAdapter(MascotaProvider.listaComidas)
                irActividad(RV_Mascota::class.java, MascotaProvider.listaComidas)
            }
        }
    }
    fun irActividad(clase: Class<*>, listaComidas: List<Comida>) {
        val intent = Intent(binding.root.context, clase)
        intent.putExtra("Lista_Comidas", ArrayList(listaComidas))
        binding.root.context.startActivity(intent)
    }


}

