package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val opcionRegistro = findViewById<TextView>(R.id.registro)
        opcionRegistro.setOnClickListener {
            irActividad(Registro::class.java)
        }

        val botonIniciarSesion = findViewById<Button>(R.id.btn_inicio)
        botonIniciarSesion.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}