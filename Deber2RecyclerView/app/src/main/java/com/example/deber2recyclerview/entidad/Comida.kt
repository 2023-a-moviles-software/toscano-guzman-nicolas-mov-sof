package com.example.deber2recyclerview.entidad

import java.io.Serializable

data class Comida(
    val nombreComida: String,
    val precio: Double,
    val imagen: String
) : Serializable