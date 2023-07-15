package com.example.exam_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class VistaJugador : AppCompatActivity() {

    private var equipoId: Int = 0
    private var idItemSeleccionado = 0

    private lateinit var tituloEquipo: TextView
    private lateinit var equipo: Equipo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_jugador)
        equipoId = intent.getIntExtra("idEquipo", 0)

        tituloEquipo = findViewById(R.id.tituloEquipo)

        equipo = BaseDeDatos.arregloEquipo.find { it.id == equipoId }!!


        val listView = findViewById<ListView>(R.id.listView_equipo_completo)
        tituloEquipo.text = equipo.nombreEquipo
        val detalleEquipo = mutableListOf<String>()
        detalleEquipo.add("Jugadores:")


        for (jugador in equipo.jugadorObtenido) {
            detalleEquipo.add(jugador.toString())
        }
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            // Convertir el equipo a una lista para el adaptador
            detalleEquipo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_jugador)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearJugador::class.java, -1,-1)
        }

        registerForContextMenu(listView)
    }

    private fun irActividad(clase: Class<*>, idEquipo: Int, posicion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo)
        intent.putExtra("posicion", posicion) // Pasar la posición del equipo seleccionado
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_Editar -> {
                val jugadorId = idItemSeleccionado
                val intent = Intent(
                    this,
                    EditarJugador::class.java
                )
                intent.putExtra("jugadorId", jugadorId)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {

                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }

            else
            -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.id.toInt()
        idItemSeleccionado = id
    }

    private fun actualizarListaJugadores() {
        val listView = findViewById<ListView>(R.id.listView_equipo_completo)
        tituloEquipo.text = equipo.nombreEquipo
        val detalleEquipo = mutableListOf<String>()
        detalleEquipo.add("Jugadores:")
        for (jugador in equipo.jugadorObtenido) {
            detalleEquipo.add(jugador.toString())
        }
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            detalleEquipo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onRestart() {
        super.onRestart()
        actualizarListaJugadores()
    }

    override fun onResume() {
        super.onResume()
        actualizarListaJugadores()
    }
}