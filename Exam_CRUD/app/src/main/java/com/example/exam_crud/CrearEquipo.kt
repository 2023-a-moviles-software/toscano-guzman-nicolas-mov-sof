package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText

class CrearEquipo : AppCompatActivity() {

    private val arreglo = BaseDeDatos.arregloEquipo
    private var equipoId: Int = 0
    private lateinit var adaptador: ArrayAdapter<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        equipoId = intent.getIntExtra("idEquipo", 0)

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
        val nuevoEquipo = Equipo(
            id = BaseDeDatos.arregloEquipo.size + 1, // Utiliza el tamaño actual de la lista como el nuevo id
            nombreEquipo = nombre,
            fundacion = fundacion,
            titulosGanados = titulos?.toInt(),
            ingresosTotales = ingresos?.toDouble(),
            jugadorObtenido = ArrayList()
        )
        BaseDeDatos.arregloEquipo.add(nuevoEquipo)
        adaptador.notifyDataSetChanged()

    }
}