package com.example.deber3sqlite.entidad

class Jugador(
    var id: Int,
    var nombreJugador: String?,
    var casado: String?,
    var edad: Int?,
    var altura: Double?,
    var posicion: String?
) {
    override fun toString(): String {
        return "${id} - ${nombreJugador} - ${casado} - ${edad} " +
                "- ${altura} - ${posicion}"
    }
}