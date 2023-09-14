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

class EditarEquipo : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_equipo)

        val intent = intent
        val id = intent.getIntExtra("equipoId", -1)
        val nombreEquipo = intent.getStringExtra("nombreEquipo")
        val fundacionEquipo = intent.getStringExtra("fundacionEquipo")
        val trofeosEquipo = intent.getIntExtra("trofeosEquipo", 0)
        val ingresosEquipo = intent.getDoubleExtra("ingresosEquipo", 0.0)

        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipoEditar)
        tituloEquipoEditar.text = nombreEquipo

        val nombreEditText = findViewById<EditText>(R.id.editarTextNombre)
        nombreEditText.setText(nombreEquipo)

        val fundacionEditText = findViewById<EditText>(R.id.editarTextFundacion)
        fundacionEditText.setText(fundacionEquipo)

        val titulosEditText = findViewById<EditText>(R.id.editarTextTitulos)
        val titulos = trofeosEquipo.toString()
        titulosEditText.setText(titulos)

        val ingresosEditText = findViewById<EditText>(R.id.editarTextIngresos)
        val ingresos = ingresosEquipo.toString()
        ingresosEditText.setText(ingresos)

        val botonActualizarEquipo = findViewById<Button>(R.id.btn_actualizar_equipo)
        botonActualizarEquipo
            .setOnClickListener {
                val nombreNuevo = findViewById<EditText>(R.id.editarTextNombre).text.toString()
                val fundacionNuevo =
                    findViewById<EditText>(R.id.editarTextFundacion).text.toString()
                val titulosNuevo = findViewById<EditText>(R.id.editarTextTitulos).text.toString()
                val ingresosNuevo = findViewById<EditText>(R.id.editarTextIngresos).text.toString()

                val db = Firebase.firestore
                val referenciaEquipos = db.collection("equipos")

                val nuevosDatos = hashMapOf<String, Any?>(
                    "id" to id,
                    "nombre" to nombreNuevo,
                    "fundacion" to fundacionNuevo,
                    "trofeos" to titulosNuevo.toInt(),
                    "ingresos" to ingresosNuevo.toDouble(),
                    "jugadores" to listOf(""),
                )

                referenciaEquipos.document(id.toString()).update(nuevosDatos)
                    .addOnSuccessListener {
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.e("ERROR", "Error al actualizar equipo: $e")
                    }

            }


    }
}