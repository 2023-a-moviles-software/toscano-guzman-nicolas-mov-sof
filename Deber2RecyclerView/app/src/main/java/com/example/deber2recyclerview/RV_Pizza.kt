package com.example.deber2recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deber2recyclerview.adapter.CategoriaAdapter
import com.example.deber2recyclerview.adapter.ComidaAdapter
import com.example.deber2recyclerview.databinding.ActivityMainBinding
import com.example.deber2recyclerview.entidad.Comida
import com.example.deber2recyclerview.provider.CategoriaProvider

class RV_Pizza : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.item_comida_pizza)


        val listaComidas = intent.getSerializableExtra("Lista_Comidas") as? ArrayList<Comida>

        if (listaComidas != null) {
            val adapter = ComidaAdapter(listaComidas)
            binding.recyclerViewComida.layoutManager = LinearLayoutManager(this)
            val comidaAdapter = CategoriaAdapter(CategoriaProvider.listaCategorias, binding)
            binding.recyclerViewComida.adapter = comidaAdapter
        }
    }
}