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
        var casadoString: String?

        if(casado == true){
            casadoString = "Casado"
        }else{
            casadoString = "Soltero"
        }

        return "${id} - ${nombreJugador} - ${casadoString} - ${edad} " +
                "- ${altura} - ${posicion} - ${equipoJugador}"
    }
}