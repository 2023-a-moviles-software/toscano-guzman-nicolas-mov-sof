package com.example.deber3sqlite

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
        EBaseDeDatos.tabla = ESqliteHelperEquipo(this)

        equipoId = intent.getIntExtra("idEquipo", 0)


        equipo = EBaseDeDatos.tabla!!.mostrarEquipos().find { it.id == equipoId }!!

        equipo = EBaseDeDatos.tabla!!.mostrarEquipos().find { it.id == equipoId }!!

        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipo)
        tituloEquipoEditar.text = equipo.nombreEquipo

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            equipo.jugadorObtenido
        )
        listView.adapter = adaptador

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_jugador)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearJugador::class.java, equipoId, -1)
        }

        registerForContextMenu(listView)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_Editar -> {
                val jugadorId = idItemSeleccionado
                val intent = Intent(this, EditarJugador::class.java)
                intent.putExtra("equipoId", equipoId)
                intent.putExtra("jugadorId", jugadorId)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {
                val jugador = equipo.jugadorObtenido.find { it.id == idItemSeleccionado }
                if (jugador != null) {
                    EBaseDeDatos.tabla!!.eliminarJugadorFormulario(idItemSeleccionado)
//                    eliminarJugador(jugador)
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
        val jugadoresEquipo = EBaseDeDatos.tabla!!.mostrarJugadores(equipo.id)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            jugadoresEquipo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
//    private fun eliminarJugador(jugador: Jugador) {
//        equipo.jugadorObtenido.remove(jugador)
//    }

    override fun onRestart() {
        super.onRestart()
        actualizarListaJugadores()
    }

    override fun onResume() {
        super.onResume()
        actualizarListaJugadores()
    }

    private fun irActividad(clase: Class<*>, idEquipo: Int, posicion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo)
        intent.putExtra("posicion", posicion)
        startActivity(intent)
    }
}

