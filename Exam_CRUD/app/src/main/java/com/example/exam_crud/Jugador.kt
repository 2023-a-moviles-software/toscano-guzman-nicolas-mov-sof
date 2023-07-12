package com.example.exam_crud

class Jugador(
    var id: Int,
    var nombreJugador: String?,
    var casado: Boolean?,
    var edad: Int?,
    var altura: Double?,
    var posicion: String?,
    var equipoJugador: String?
) {
    override fun toString(): String {
        return "${id} - ${nombreJugador} - ${casado} - ${edad} " +
                "- ${altura} - ${posicion} - ${equipoJugador}"
    }
}