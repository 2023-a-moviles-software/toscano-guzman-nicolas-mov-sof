package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearJugador : AppCompatActivity() {

    private var equipoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)

        equipoId = intent.getIntExtra("idEquipo", equipoId + 1)

        val botonCrearJugador = findViewById<Button>(R.id.btn_crearJugador)

        botonCrearJugador.setOnClickListener {
            val nombreJugador = findViewById<EditText>(R.id.tv_crearJugador_nombre).text.toString()
            val casadoJugador = findViewById<EditText>(R.id.tv_crearJugador_casado).text.toString()
            val edadJugador = findViewById<EditText>(R.id.tv_crearJugador_edad).text.toString()
            val alturaJugador = findViewById<EditText>(R.id.tv_crearJugador_altura).text.toString()
            val posicionJugador =
                findViewById<EditText>(R.id.tv_crearJugador_posicion).text.toString()

            EBaseDeDatos.tabla!!.crearJugador(
                nombreJugador,
                casadoJugador,
                edadJugador,
                alturaJugador,
                posicionJugador,
                equipoId.toString()
            )
//            val intent = Intent(this,VistaJugador::class.java)
//            intent.putExtra("idEquipo",equipoId)
//            startActivity(intent)
            finish()
        }
    }
}