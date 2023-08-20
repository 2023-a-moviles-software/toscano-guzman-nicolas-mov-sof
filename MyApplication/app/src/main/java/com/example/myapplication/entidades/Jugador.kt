package com.example.myapplication.entidades

class Jugador(
    var id: Int,
    var nombreJugador: String?,
    var casado: String?,
    var edad: String?,
    var altura: String?,
    var posicion: String?,
    var idEquipo: Int
) {
    override fun toString(): String {
        return "${id} - ${nombreJugador} - ${casado} - ${edad} " +
                "- ${altura} - ${posicion}- ${idEquipo}"
    }
}