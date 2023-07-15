package com.example.exam_crud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarEquipo : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_equipo)

        val intent = intent
        val equipoId = intent.getIntExtra("equipoId", 0)

        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipoEditar)

        val equipo = BaseDeDatos.equipos.find { it.id == equipoId }

        if (equipo != null) {
            tituloEquipoEditar.text = equipo.nombreEquipo

            val nombreEditText = findViewById<EditText>(R.id.editarTextNombre)
            nombreEditText.setText(equipo.nombreEquipo)

            val fundacionEditText = findViewById<EditText>(R.id.editarTextFundacion)
            fundacionEditText.setText(equipo.fundacion)

            val titulosEditText = findViewById<EditText>(R.id.editarTextTitulos)
            val titulos = equipo.titulosGanados?.toString() ?: ""
            titulosEditText.setText(titulos)

            val ingresosEditText = findViewById<EditText>(R.id.editarTextIngresos)
            val ingresos = equipo.ingresosTotales?.toString() ?: ""
            ingresosEditText.setText(ingresos)
        }

        val botonActualizarEquipo = findViewById<Button>(R.id.btn_actualizar_equipo)
        botonActualizarEquipo
            .setOnClickListener {
                val nombre = findViewById<EditText>(R.id.editarTextNombre).text.toString()
                val fundacion = findViewById<EditText>(R.id.editarTextFundacion).text.toString()
                val titulos = findViewById<EditText>(R.id.editarTextTitulos).text.toString()
                val ingresos = findViewById<EditText>(R.id.editarTextIngresos).text.toString()

                val equipo = BaseDeDatos.equipos.find { it.id == equipoId }
                if (equipo != null) {
                    equipo.nombreEquipo = nombre
                    equipo.fundacion = fundacion
                    equipo.titulosGanados = titulos.toIntOrNull()
                    equipo.ingresosTotales = ingresos.toDoubleOrNull()
                }
                finish()
            }

    }
}