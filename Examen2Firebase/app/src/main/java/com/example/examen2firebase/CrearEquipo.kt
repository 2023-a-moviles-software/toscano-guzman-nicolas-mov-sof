package com.example.examen2firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearEquipo : AppCompatActivity() {

    private var equipoId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)

        equipoId = intent.getIntExtra("idEquipo", equipoId + 1)

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crearEquipo)

        botonCrearEquipo.setOnClickListener {

            val nombreEquipo = findViewById<EditText>(R.id.crearTextNombre).text.toString()
            val fundacionEquipo = findViewById<EditText>(R.id.crearTextFundacion).text.toString()
            val titulosEquipo = findViewById<EditText>(R.id.crearTextTitulos).text.toString()
            val ingresosEquipo = findViewById<EditText>(R.id.crearTextIngresos).text.toString()

            crearEquipo(
                nombreEquipo,
                fundacionEquipo,
                titulosEquipo.toInt(),
                ingresosEquipo.toDouble()
            )
        }
    }

    private fun crearEquipo(
        nombre: String?,
        fundacion: String?,
        titulos: Int?,
        ingresos: Double?
    ) {
        val db = Firebase.firestore
        val referenciaEquipos = db
            .collection("equipos")

        referenciaEquipos.get().addOnSuccessListener { querySnapshot ->
            val nuevoId = querySnapshot.size() + 1

            val datosEquipo = hashMapOf(
                "id" to nuevoId,
                "nombre" to nombre,
                "fundacion" to fundacion,
                "trofeos" to titulos,
                "ingresos" to ingresos,
                "jugadores" to listOf(""),
            )
            if (nombre != null) {
                referenciaEquipos.document(nuevoId.toString()).set(datosEquipo)
            }
            finish()
        }
    }
}
