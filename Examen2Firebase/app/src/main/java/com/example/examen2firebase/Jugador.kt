package com.example.examen2firebase

class Jugador(
    var id: Int?,
    var nombreJugador: String?,
    var casado: Boolean?,
    var edad: Int?,
    var altura: Double?,
    var posicion: String?,
    var equipoJugador: String?
) {
    override fun toString(): String {
        return "${nombreJugador} - ${edad} años - ${posicion} - ${altura} mts"
    }
}