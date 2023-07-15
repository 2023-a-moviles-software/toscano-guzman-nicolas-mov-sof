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

class VistaEquipo : AppCompatActivity() {

    private var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_equipo)

        val listView = findViewById<ListView>(R.id.listView_Equipo)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatos.equipos
        )

        listView.adapter = adaptador

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = adaptador.getItem(position)
                val idEquipo = selectedItem?.id
                if (idEquipo != null) {
                    irActividad(VistaJugador::class.java, idEquipo, position)
                }
            }
        adaptador.notifyDataSetChanged()

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_equipo)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearEquipo::class.java, -1, -1) // Utiliza valores distintos para el nuevo equipo
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
                val equipoId = idItemSeleccionado
                val intent = Intent(
                    this,
                    EditarEquipo::class.java
                )
                intent.putExtra("equipoId",equipoId)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {
                val equipo = BaseDeDatos.equipos.find { it.id == idItemSeleccionado }
                if (equipo != null) {
                    eliminarEquipo(equipo)
                    actualizarListaEquipos()
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
        val menuId = BaseDeDatos.equipos[info.position].id
        idItemSeleccionado = menuId
    }

    private fun actualizarListaEquipos() {
        val listView = findViewById<ListView>(R.id.listView_Equipo)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDeDatos.equipos
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    private fun eliminarEquipo(equipo: Equipo) {
        BaseDeDatos.equipos.remove(equipo)
    }

    override fun onRestart() {
        super.onRestart()
        actualizarListaEquipos()
    }

    override fun onResume() {
        super.onResume()
        actualizarListaEquipos()
    }



}

