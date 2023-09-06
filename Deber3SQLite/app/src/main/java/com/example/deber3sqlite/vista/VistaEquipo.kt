package com.example.deber3sqlite.vista

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
import com.example.deber3sqlite.R
import com.example.deber3sqlite.bdsqlite.EBaseDeDatos
import com.example.deber3sqlite.bdsqlite.ESqliteHelperEquipo
import com.example.deber3sqlite.crud.CrearEquipo
import com.example.deber3sqlite.crud.EditarEquipo
import com.example.deber3sqlite.entidad.Equipo

class VistaEquipo : AppCompatActivity() {

    private var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_equipo)

        val listView = findViewById<ListView>(R.id.listView_Equipo)
        EBaseDeDatos.tabla = ESqliteHelperEquipo(this)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EBaseDeDatos.tabla!!.mostrarEquipos()
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
            irActividad(CrearEquipo::class.java, -1, -1)
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
                intent.putExtra("equipoId", equipoId)
                startActivityForResult(intent, 1)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {
                EBaseDeDatos.tabla!!.eliminarEquipoFormulario(idItemSeleccionado)
                actualizarListaEquipos()
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
//        val menuId = BaseDeDatos.equipos[info.position].id
        val menuId = EBaseDeDatos.tabla!!.mostrarEquipos()[info.position].id
        idItemSeleccionado = menuId
    }

    private fun actualizarListaEquipos() {
        adaptador.clear() // Limpia la lista actual del adaptador
        adaptador.addAll(EBaseDeDatos.tabla!!.mostrarEquipos()) // Agrega los nuevos datos
        adaptador.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }

    override fun onRestart() {
        super.onRestart()
        actualizarListaEquipos()
    }

    override fun onResume() {
        super.onResume()
        actualizarListaEquipos()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Actualiza la lista de equipos aquí
            actualizarListaEquipos()
        }
    }
}

