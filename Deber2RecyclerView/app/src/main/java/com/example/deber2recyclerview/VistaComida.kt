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
    private lateinit var nombreCategoria: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nombreCategoria = intent.getStringExtra("Categoria_nombre") ?: ""
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerViewComida.layoutManager = LinearLayoutManager(this)
        // Encuentra la categoría seleccionada
        val categoriaSeleccionada =
            BaseDeDatos.listaCategorias.find { it.nombreCategoria == nombreCategoria }
        val listaComidas = mutableListOf<Comida>()
        // Verifica si se encontró la categoría seleccionada y agrega sus comidas a la lista
        categoriaSeleccionada?.let {
            listaComidas.addAll(it.comida)
        }
        val comidaAdapter = ComidaAdapter(listaComidas)
        binding.recyclerViewComida.adapter = comidaAdapter
    }

}

