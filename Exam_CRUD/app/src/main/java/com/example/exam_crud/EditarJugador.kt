package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class EditarJugador : AppCompatActivity() {

    private lateinit var adaptador: ArrayAdapter<Jugador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_jugador)

        val listView8 = findViewById<ListView>(R.id.listaJugadoresActualizada)

        val intent = intent
        val jugadorId = intent.getIntExtra("jugadorId", 0)

        val tvEditarNombreJugador = findViewById<TextView>(R.id.tv_editar_nombreJugador)

        val jugador = BaseDeDatos.arregloJugadorFC.find { it.id == jugadorId }

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatos.arregloJugadorFC
        )

        listView8.adapter = adaptador

        if (jugador != null) {
            tvEditarNombreJugador.text = jugador.nombreJugador

            val tvEditarCasado = findViewById<EditText>(R.id.tv_editar_casado)
            val casado = jugador.casado?.toString() ?: ""
            tvEditarCasado.setText(casado)
            val tvEditarEdad = findViewById<EditText>(R.id.tv_editar_edad)
            tvEditarEdad.setText(jugador.edad.toString())
            val tvEditarAltura = findViewById<EditText>(R.id.tv_editar_altura)
            tvEditarAltura.setText(jugador.altura.toString())
            val tvEditarPosicion = findViewById<EditText>(R.id.tv_editar_posicion)
            tvEditarPosicion.setText(jugador.posicion)
            val tvEditarEquipo = findViewById<EditText>(R.id.tv_editar_equipo)
            tvEditarEquipo.setText(jugador.equipoJugador)
        }

        val botonActualizarJugador = findViewById<Button>(R.id.btn_actualizar_jugador)
        botonActualizarJugador
            .setOnClickListener {
                val casado = findViewById<EditText>(R.id.tv_editar_casado).text.toString()
                val edad = findViewById<EditText>(R.id.tv_editar_edad).text.toString()
                val altura = findViewById<EditText>(R.id.tv_editar_altura).text.toString()
                val posicion = findViewById<EditText>(R.id.tv_editar_posicion).text.toString()
                val equipo = findViewById<EditText>(R.id.tv_editar_equipo).text.toString()

                val casadoBoolean: Boolean = casado == "true"
                val jugador = BaseDeDatos.arregloJugadorFC.find { it.id == jugadorId }
                if (jugador!=null){
                    jugador.casado = casadoBoolean
                    jugador.edad = edad.toIntOrNull()
                    jugador.altura = altura.toDoubleOrNull()
                    jugador.posicion = posicion
                    jugador.equipoJugador = equipo
                }
            }
        registerForContextMenu(listView8)
    }
}
