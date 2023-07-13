package com.example.exam_crud

class BaseDeDatos {

    companion object {

        val arregloJugadorFC = arrayListOf<Jugador>()

        init {
            arregloJugadorFC.add(
                Jugador(1, "Lionel Messi", false, 34, 1.70,
                    "Delantero", "FC Barcelona")
            )

            arregloJugadorFC.add(
                Jugador(2, "Cristiano Ronaldo", true, 36, 1.87,
                    "Delantero", "Manchester United")
            )

            arregloJugadorFC.add(
                Jugador(3, "Neymar Jr.", true, 29, 1.75,
                    "Delantero", "Paris Saint-Germain")
            )

            arregloJugadorFC.add(
                Jugador(4, "Kevin De Bruyne", false, 30, 1.81,
                    "Centrocampista", "Manchester City")
            )

            arregloJugadorFC.add(
                Jugador(5, "Virgil van Dijk", true, 30, 1.93,
                    "Defensor", "Liverpool")
            )
        }

        val arregloJugadorRM = arrayListOf<Jugador>()

        init {
            arregloJugadorRM.add(
                Jugador(1, "Kylian Mbappé", false, 22, 1.78,
                    "Delantero", "Paris Saint-Germain")
            )

            arregloJugadorRM.add(
                Jugador(2, "Robert Lewandowski", true, 32, 1.84,
                    "Delantero", "Bayern Munich")
            )

            arregloJugadorRM.add(
                Jugador(3, "Kevin Mbouandjo", true, 32, 1.87,
                    "Delantero", "FC Lorient")
            )

            arregloJugadorRM.add(
                Jugador(4, "Toni Kroos", true, 31, 1.83,
                    "Centrocampista", "Real Madrid")
            )

            arregloJugadorRM.add(
                Jugador(5, "Virgil van Dijk", false, 30, 1.93,
                    "Defensor", "Liverpool")
            )

        }

        val arregloJugadorLV = arrayListOf<Jugador>()

        init {

            arregloJugadorLV.add(
                Jugador(1, "Sergio Ramos", true, 35, 1.84,
                    "Defensor", "Paris Saint-Germain")
            )

            arregloJugadorLV.add(
                Jugador(2, "Luka Modric", true, 35, 1.72,
                    "Centrocampista", "Real Madrid")
            )

            arregloJugadorLV.add(
                Jugador(3, "Harry Kane", true, 28, 1.88,
                    "Delantero", "Tottenham Hotspur")
            )

            arregloJugadorLV.add(
                Jugador(4, "Manuel Neuer", true, 35, 1.93,
                    "Portero", "Bayern Munich")
            )

            arregloJugadorLV.add(
                Jugador(5, "Mohamed Salah", true, 29, 1.75,
                    "Delantero", "Liverpool")
            )
        }

        val arregloEquipo = arrayListOf<Equipo>()

        init {
            arregloEquipo
                .add(
                    Equipo(1, "FC Barcelona", "2018-12-12", 34,
                        1000000.0, arregloJugadorFC)
                )
            arregloEquipo
                .add(
                    Equipo(2, "Real Madrid", "2018-12-12", 12,
                        1500000.0, arregloJugadorRM)
                )
            arregloEquipo
                .add(
                    Equipo(3, "Liverpool", "2018-12-12", 50,
                        2500000.0, arregloJugadorLV)
                )
        }
    }

}