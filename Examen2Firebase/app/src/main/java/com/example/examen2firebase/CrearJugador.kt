package com.example.examen2firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearJugador : AppCompatActivity() {

    private var idJugador: Int = 0
    private var nombreEquipo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)

        idJugador = intent.getIntExtra("idEquipo", idJugador + 1)
        nombreEquipo = intent.getStringExtra("nombreEquipo")

        val botonCrearJugador = findViewById<Button>(R.id.btn_crearJugador)

        botonCrearJugador.setOnClickListener {
            val nombreJugador = findViewById<EditText>(R.id.tv_crearJugador_nombre).text.toString()
            val casadoJugador = findViewById<EditText>(R.id.tv_crearJugador_casado).text.toString()
            val edadJugador = findViewById<EditText>(R.id.tv_crearJugador_edad).text.toString()
            val alturaJugador = findViewById<EditText>(R.id.tv_crearJugador_altura).text.toString()
            val posicionJugador =
                findViewById<EditText>(R.id.tv_crearJugador_posicion).text.toString()

            val casadoJugadorBoolean: Boolean

            casadoJugadorBoolean = casadoJugador == "casado" || casadoJugador == "Casado"

            val equipoDelJugador = nombreEquipo

            crearJugador(
                nombreJugador,
                casadoJugadorBoolean,
                edadJugador.toInt(),
                alturaJugador.toDouble(),
                posicionJugador,
                equipoDelJugador
            )
        }
    }

    private fun crearJugador(
        nombre: String?,
        casado: Boolean?,
        edad: Int?,
        altura: Double?,
        posicion: String?,
        equipoDelJugador: String?
    ) {
        nombreEquipo = intent.getStringExtra("nombreEquipo")

        val db = Firebase.firestore
        val referenciaJugadores = db
            .collection("jugadores")

        referenciaJugadores.get().addOnSuccessListener { querySnapshot ->
            val nuevoId = querySnapshot.size() + 1

            val datosEquipo = hashMapOf(
                "id" to nuevoId,
                "nombreJugador" to nombre,
                "casado" to casado,
                "edad" to edad,
                "altura" to altura,
                "posicion" to posicion,
                "equipoDelJugador" to equipoDelJugador
            )
            if (nombre != null) {
                referenciaJugadores.document(nuevoId.toString()).set(datosEquipo)
            }
            finish()
        }


    }

}