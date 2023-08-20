package com.example.deber3sqlite.entidad

class Equipo(
    var id: Int,
    var nombreEquipo: String?,
    var fundacion: String?,
    var titulosGanados: Int?,
    var ingresosTotales: Double?,
    var jugadorObtenido: MutableList<Jugador>
) {
    override fun toString(): String {
        return "${id} - ${nombreEquipo}"
    }
}