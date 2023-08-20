package com.example.myapplication.entidades

class Equipo(
    var id: Int,
    var nombreEquipo: String?,
    var fundacion: String?,
    var titulosGanados: String?,
    var ingresosTotales: String?,
    var jugadorObtenido: String?
) {
    override fun toString(): String {
        return "${id} - ${nombreEquipo}"
    }
}