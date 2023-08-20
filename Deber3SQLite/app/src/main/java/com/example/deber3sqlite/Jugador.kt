package com.example.deber3sqlite

class Jugador(
    var id: Int,
    var nombreJugador: String?,
    var casado: String?,
    var edad: Int?,
    var altura: Double?,
    var posicion: String?
) {
    override fun toString(): String {
//        val casadoString: String?
//
//        if(casado == true){
//            casadoString = "Casado"
//        }else{
//            casadoString = "Soltero"
//        }

        return "${id} - ${nombreJugador} - ${casado} - ${edad} " +
                "- ${altura} - ${posicion}"
    }
}