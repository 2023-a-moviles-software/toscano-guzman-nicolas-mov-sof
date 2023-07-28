package com.example.deber2recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deber2recyclerview.adapter.CategoriaAdapter
import com.example.deber2recyclerview.databinding.RecyclerViewCategoriaBinding
import com.example.deber2recyclerview.BD.BaseDeDatos


class VistaCategoria : AppCompatActivity() {

    private lateinit var binding: RecyclerViewCategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerViewCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {

        //val manager = LinearLayoutManager(this)
        //val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerViewCategoria.layoutManager = LinearLayoutManager(this)
        val categoriaAdapter = CategoriaAdapter(BaseDeDatos.listaCategorias)
        binding.recyclerViewCategoria.adapter = categoriaAdapter

        //binding.recyclerViewCategoria.addItemDecoration(decoration)
    }

}