package com.example.deber2recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deber2recyclerview.adapter.ComidaAdapter
import com.example.deber2recyclerview.databinding.RecyclerViewComidaBinding
import com.example.deber2recyclerview.entidad.Comida
import com.example.deber2recyclerview.BD.BaseDeDatos

class VistaComida : AppCompatActivity() {

    private lateinit var binding: RecyclerViewComidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewComida.layoutManager = LinearLayoutManager(this)

        // Combina todas las listas de comidas de cada categoría en una sola lista
        val listaComidas = mutableListOf<Comida>()
        for (categoria in BaseDeDatos.listaCategorias) {
            listaComidas.addAll(categoria.comida)
        }

        val comidaAdapter = ComidaAdapter(listaComidas)
        binding.recyclerViewComida.adapter = comidaAdapter
    }
}

