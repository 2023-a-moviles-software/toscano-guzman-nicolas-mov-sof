package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class CrearJugador : AppCompatActivity() {

    private var equipoId: Int = 0
    private lateinit var equipo: Equipo
    private lateinit var adaptador: ArrayAdapter<Jugador>
    private lateinit var jugadoresEquipo: ArrayList<Jugador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)

        val listView8 = findViewById<ListView>(R.id.lista_jugador_actualizada)

        equipoId = intent.getIntExtra("idJugador", 0)
        equipo = obtenerEquipoPorId(equipoId) ?: return // Obtener el equipo correspondiente al equipoId
        jugadoresEquipo = equipo.jugadorObtenido

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            jugadoresEquipo
        )

        listView8.adapter = adaptador

        val botonCrearJugador = findViewById<Button>(R.id.btn_crearJugador)

        botonCrearJugador.setOnClickListener {
            val nombreJugador = findViewById<EditText>(R.id.tv_crearJugador_nombre).text.toString()
            val casadoJugador = findViewById<EditText>(R.id.tv_crearJugador_casado).text.toString()
            val edadJugador = findViewById<EditText>(R.id.tv_crearJugador_edad).text.toString()
            val alturaJugador = findViewById<EditText>(R.id.tv_crearJugador_altura).text.toString()
            val posicionJugador = findViewById<EditText>(R.id.tv_crearJugador_posicion).text.toString()
            crearJugador(
                nombreJugador,
                casadoJugador,
                edadJugador,
                alturaJugador,
                posicionJugador
            )
        }
        registerForContextMenu(listView8)
    }

    private fun crearJugador(
        nombre: String?,
        casado: String?,
        edad: String?,
        altura: String?,
        posicion: String?
    ) {
        val equipo = BaseDeDatos.arregloEquipo.find { it.id == equipoId } ?: return

        val nuevoJugador = Jugador(
            id = BaseDeDatos.arregloJugadorFC.size + 1, // Utiliza el tamaño actual del arreglo como el nuevo id
            nombreJugador = nombre,
            casado = casado.toBoolean(),
            edad = edad?.toInt(),
            altura = altura?.toDouble(),
            posicion = posicion,
            equipoJugador = equipo.nombreEquipo ?: ""
        )
        equipo.jugadorObtenido.add(nuevoJugador)
        adaptador.notifyDataSetChanged()
    }

    private fun obtenerEquipoPorId(id: Int): Equipo? {
        return BaseDeDatos.arregloEquipo.find { it.id == id }
    }

    private fun obtenerJugadoresPorEquipoId(id: Int): ArrayList<Jugador>? {
        val equipo = BaseDeDatos.arregloEquipo.find { it.id == id }
        return equipo?.jugadorObtenido
    }

}