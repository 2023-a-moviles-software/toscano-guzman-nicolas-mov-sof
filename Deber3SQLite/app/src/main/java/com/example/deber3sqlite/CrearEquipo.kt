package com.example.deber3sqlite

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearEquipo : AppCompatActivity() {

    private var equipoId: Int = 0
//    private lateinit var dbHelper: ESqliteHelperEquipo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)

        equipoId = intent.getIntExtra("idEquipo", equipoId+1)
//        dbHelper = ESqliteHelperEquipo(this) // Inicializa dbHelper

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crearEquipo)

        botonCrearEquipo.setOnClickListener {

            val nombreEquipo = findViewById<EditText>(R.id.crearTextNombre).text.toString()
            val fundacionEquipo = findViewById<EditText>(R.id.crearTextFundacion).text.toString()
            val titulosEquipo = findViewById<EditText>(R.id.crearTextTitulos).text.toString()
            val ingresosEquipo = findViewById<EditText>(R.id.crearTextIngresos).text.toString()


            EBaseDeDatos.tabla!!.crearEquipo(
//            dbHelper.crearEquipo(
                nombreEquipo,
                fundacionEquipo,
                titulosEquipo,
                ingresosEquipo
            )
//            startActivity(Intent(this,VistaEquipo::class.java))
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}