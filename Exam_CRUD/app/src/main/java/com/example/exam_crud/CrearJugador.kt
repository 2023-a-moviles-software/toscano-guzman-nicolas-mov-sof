package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearJugador : AppCompatActivity() {

    private var equipoId: Int = 0
    private var equipo: Equipo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)

        equipoId = intent.getIntExtra("idEquipo", -1)

        equipo = obtenerEquipoPorId(equipoId)

        val botonCrearJugador = findViewById<Button>(R.id.btn_crearJugador)

        botonCrearJugador.setOnClickListener {
            val nombreJugador = findViewById<EditText>(R.id.tv_crearJugador_nombre).text.toString()
            val casadoJugador = findViewById<EditText>(R.id.tv_crearJugador_casado).text.toString()
            val edadJugador = findViewById<EditText>(R.id.tv_crearJugador_edad).text.toString()
            val alturaJugador = findViewById<EditText>(R.id.tv_crearJugador_altura).text.toString()
            val posicionJugador = findViewById<EditText>(R.id.tv_crearJugador_posicion).text.toString()

            val casadoJugadorBoolean: Boolean

            casadoJugadorBoolean = casadoJugador == "casado" || casadoJugador == "Casado"

            crearJugador(
                nombreJugador,
                casadoJugadorBoolean,
                edadJugador,
                alturaJugador,
                posicionJugador
            )
        }
    }

    private fun crearJugador(
        nombre: String?,
        casado: Boolean?,
        edad: String?,
        altura: String?,
        posicion: String?
    ) {
        val ultimoId = equipo?.jugadorObtenido?.maxByOrNull { it.id }?.id ?: 0
        val nuevoJugador = Jugador(
            id = ultimoId + 1,
            nombreJugador = nombre,
            casado = casado,
            edad = edad?.toInt(),
            altura = altura?.toDouble(),
            posicion = posicion,
            equipoJugador = equipo?.nombreEquipo ?: ""
        )

        BaseDeDatos.equipos.find {
            it.id == equipoId
        }
            ?.jugadorObtenido?.add(nuevoJugador)
    }

    private fun obtenerEquipoPorId(id: Int): Equipo? {
        return BaseDeDatos.equipos.find { it.id == id }
    }

}