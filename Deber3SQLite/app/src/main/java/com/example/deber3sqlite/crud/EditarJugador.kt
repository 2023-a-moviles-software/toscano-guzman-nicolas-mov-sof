package com.example.deber3sqlite.crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.deber3sqlite.bdsqlite.EBaseDeDatos
import com.example.deber3sqlite.R

class EditarJugador : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_jugador)

        val intent = intent
        val jugadorId = intent.getIntExtra("jugadorId", 0)
        val equipoId = intent.getIntExtra("equipoId", 0)

        val equipo = EBaseDeDatos.tabla!!.mostrarEquipos().find { it.id == equipoId }

        val jugador = equipo?.jugadorObtenido?.find { it.id == jugadorId }

        if (jugador != null) {
            val tvEditarNombreJugador = findViewById<TextView>(R.id.tv_editar_nombreJugador)
            tvEditarNombreJugador.text = jugador.nombreJugador

            val tvEquipoJugador = findViewById<TextView>(R.id.tv_editar_equipo)
            tvEquipoJugador.text = equipo.nombreEquipo

            val tvEditarCasado = findViewById<EditText>(R.id.tv_editar_casado)
            tvEditarCasado.setText(jugador.casado.toString())

            val tvEditarEdad = findViewById<EditText>(R.id.tv_editar_edad)
            tvEditarEdad.setText(jugador.edad.toString())

            val tvEditarAltura = findViewById<EditText>(R.id.tv_editar_altura)
            tvEditarAltura.setText(jugador.altura.toString())

            val tvEditarPosicion = findViewById<EditText>(R.id.tv_editar_posicion)
            tvEditarPosicion.setText(jugador.posicion)

            val botonActualizarJugador = findViewById<Button>(R.id.btn_actualizar_jugador)
            botonActualizarJugador.setOnClickListener {
                val nuevoCasado = findViewById<EditText>(R.id.tv_editar_casado).text.toString()
                val nuevaEdad = findViewById<EditText>(R.id.tv_editar_edad).text.toString()
                val nuevaAltura = findViewById<EditText>(R.id.tv_editar_altura).text.toString()
                val nuevaPosicion = findViewById<EditText>(R.id.tv_editar_posicion).text.toString()

                EBaseDeDatos.tabla!!.editarJugadorFormulario(
                    tvEditarNombreJugador.toString(),
                    nuevoCasado,
                    nuevaEdad,
                    nuevaAltura,
                    nuevaPosicion,
                    jugadorId
                )
            }
        }
    }
}
