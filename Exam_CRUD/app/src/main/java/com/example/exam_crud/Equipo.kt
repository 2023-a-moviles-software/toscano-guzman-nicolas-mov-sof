package com.example.exam_crud

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

//        return "${id} - ${nombreEquipo} - ${fundacion} - ${titulosGanados} " +
//                "- ${ingresosTotales} - ${jugadorObtenido}"
    }


}