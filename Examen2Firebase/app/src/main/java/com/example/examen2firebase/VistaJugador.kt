package com.example.examen2firebase

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

    private lateinit var equipo: Equipo
    private var equipoId: Int = 0
    private var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Jugador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_jugador)

        val listView = findViewById<ListView>(R.id.listView_equipo_completo)

        equipoId = intent.getIntExtra("idEquipo", 0)
        equipo = BaseDeDatos.equipos.find { it.id == equipoId }!!

        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipo)
        tituloEquipoEditar.text = equipo.nombreEquipo
        //OJO
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            //OJO
            equipo.jugadorObtenido
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_jugador)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearJugador::class.java, equipoId,-1)
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
                intent.putExtra("equipoId", equipoId)
                intent.putExtra("jugadorId", jugadorId)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {
                val jugadorId = idItemSeleccionado
                val jugador = equipo.jugadorObtenido.find { it.id == jugadorId }
                if (jugador != null) {
                    eliminarJugador(jugador)
                    actualizarListaJugadores()
                }
                return true
            }

            else -> super.onContextItemSelected(item)
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
        val tituloEquipo = findViewById<TextView>(R.id.tituloEquipo)
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

    private fun eliminarJugador(jugador: Jugador) {
        equipo.jugadorObtenido.remove(jugador)
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