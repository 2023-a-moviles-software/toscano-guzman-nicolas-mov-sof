package com.example.examen2firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarJugador : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_jugador)

        val intent = intent

        val jugadorId = intent.getIntExtra("jugadorId", -1)
        val nombreJugador = intent.getStringExtra("nombreJugador")
        val casadoJugador = intent.getBooleanExtra("casadoJugador", false)
        val edadJugador = intent.getIntExtra("edadJugador", -1)
        val alturaJugador = intent.getDoubleExtra("alturaJugador", -1.0)
        val posicionJugador = intent.getStringExtra("posicionJugador")
        val equipoJugador = intent.getStringExtra("equipoJugador")

        val tvEditarNombreJugador = findViewById<TextView>(R.id.tv_editar_nombreJugador)
        tvEditarNombreJugador.text = nombreJugador

        val tvEditarCasado = findViewById<EditText>(R.id.tv_editar_casado)
        tvEditarCasado.setText(casadoJugador.toString())

        val tvEditarEdad = findViewById<EditText>(R.id.tv_editar_edad)
        tvEditarEdad.setText(edadJugador.toString())

        val tvEditarAltura = findViewById<EditText>(R.id.tv_editar_altura)
        tvEditarAltura.setText(alturaJugador.toString())

        val tvEditarPosicion = findViewById<EditText>(R.id.tv_editar_posicion)
        tvEditarPosicion.setText(posicionJugador.toString())

        val tvEditarEquipo = findViewById<EditText>(R.id.tv_editar_equipo)
        tvEditarEquipo.setText(equipoJugador.toString())

        val botonActualizarJugador = findViewById<Button>(R.id.btn_actualizar_jugador)
        botonActualizarJugador.setOnClickListener {
            val nuevoCasado = findViewById<EditText>(R.id.tv_editar_casado).text.toString()
            val nuevoEdad = findViewById<EditText>(R.id.tv_editar_edad).text.toString()
            val nuevoAltura = findViewById<EditText>(R.id.tv_editar_altura).text.toString()
            val nuevoPosicion = findViewById<EditText>(R.id.tv_editar_posicion).text.toString()
            val nuevoEquipo = findViewById<EditText>(R.id.tv_editar_equipo).text.toString()

            val db = Firebase.firestore
            val referenciaJugadores = db.collection("jugadores")

            val nuevosDatos = hashMapOf<String, Any?>(
                "id" to jugadorId,
                "nombreJugador" to nombreJugador,
                "casado" to nuevoCasado.toBoolean(),
                "edad" to nuevoEdad.toInt(),
                "altura" to nuevoAltura.toDouble(),
                "posicion" to nuevoPosicion,
                "equipoDelJugador" to nuevoEquipo
            )

            referenciaJugadores.document(jugadorId.toString()).update(nuevosDatos)
                .addOnSuccessListener {
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.e("ERROR", "Error al actualizar jugador: $e")
                }
        }
    }
}
