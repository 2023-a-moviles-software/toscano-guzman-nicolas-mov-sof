package com.example.myapplication

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
import com.example.myapplication.entidades.Equipo

class VistaEquipos : AppCompatActivity() {

    private var idItemSeleccionado = 0
    private lateinit var adaptador: ArrayAdapter<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_equipos)

        val listView = findViewById<ListView>(R.id.listView_Equipo)

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
                    irActividad(VistaJugadores::class.java, idEquipo, position)
                }
            }
        adaptador.notifyDataSetChanged()

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_equipo)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearEquipo::class.java, -1, -1)
        }
        registerForContextMenu(listView)
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
        val menuId = EBaseDeDatos.tabla!!.mostrarEquipos()[info.position].id
        idItemSeleccionado = menuId

    }

    private fun actualizarListaEquipos() {
        val listView = findViewById<ListView>(R.id.listView_Equipo)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EBaseDeDatos.tabla!!.mostrarEquipos()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    override fun onRestart() {
        super.onRestart()
        actualizarListaEquipos()
    }

    override fun onResume() {
        super.onResume()
        actualizarListaEquipos()
    }

    private fun irActividad(clase: Class<*>, idEquipo: Int, posicion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo)
        intent.putExtra("posicion", posicion) // Pasar la posición del equipo seleccionado
        startActivity(intent)
    }
}