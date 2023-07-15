package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearEquipo : AppCompatActivity() {

    private var equipoId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)

        equipoId = intent.getIntExtra("idEquipo", equipoId+1)

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crearEquipo)

        botonCrearEquipo.setOnClickListener {

            val nombreEquipo = findViewById<EditText>(R.id.crearTextNombre).text.toString()
            val fundacionEquipo = findViewById<EditText>(R.id.crearTextFundacion).text.toString()
            val titulosEquipo = findViewById<EditText>(R.id.crearTextTitulos).text.toString()
            val ingresosEquipo = findViewById<EditText>(R.id.crearTextIngresos).text.toString()

            crearEquipo(
                nombreEquipo,
                fundacionEquipo,
                titulosEquipo,
                ingresosEquipo
            )
        }
    }

    private fun crearEquipo(
        nombre: String?,
        fundacion: String?,
        titulos: String?,
        ingresos: String?
    ) {
        val ultimoId = BaseDeDatos.equipos.lastOrNull()?.id ?: 0
        val nuevoEquipo = Equipo(
            id = ultimoId + 1,
            nombreEquipo = nombre,
            fundacion = fundacion,
            titulosGanados = titulos?.toInt(),
            ingresosTotales = ingresos?.toDouble(),
            jugadorObtenido = ArrayList()
        )
        BaseDeDatos.equipos.add(nuevoEquipo)
    }
}