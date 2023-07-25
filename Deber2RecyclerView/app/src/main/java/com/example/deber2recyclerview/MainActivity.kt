package com.example.deber2recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deber2recyclerview.adapter.ComidaAdapter
import com.example.deber2recyclerview.databinding.ActivityMainBinding
import com.example.deber2recyclerview.provider.PizzaProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){

        binding.recyclerViewComida.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewComida.adapter = ComidaAdapter(PizzaProvider.listaComidas)
    }
}