package com.example.deber2recyclerview.provider

import com.example.deber2recyclerview.Categoria

class CategoriaProvider {
    companion object{
        val listaCategorias: List<Categoria> = listOf(
            Categoria("Pizza", ""),
            Categoria("Alcohol", ""),
            Categoria("Farmacia", ""),
            Categoria("Hamburguesas", ""),
            Categoria("Mascotas", "")
        )
    }
}