package com.example.movilessoftware2023a

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Nicolás","nicolas@h.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Jaime","jaime@h.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carlina","carolina@h.com")
                )
        }
    }
}