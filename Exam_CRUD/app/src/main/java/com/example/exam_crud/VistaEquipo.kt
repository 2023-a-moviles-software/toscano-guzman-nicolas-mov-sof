package com.example.exam_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class VistaEquipo : AppCompatActivity() {

    val arreglo = BaseDeDatos.arregloEquipo
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_equipo)

        val listView = findViewById<ListView>(R.id.listView_Equipo)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = adaptador.getItem(position)
            val idEquipo = selectedItem?.id
            if (idEquipo != null) {
                irActividad(info_equipo::class.java, idEquipo)
            }
        }

        adaptador.notifyDataSetChanged()


        registerForContextMenu(listView)


    }

    fun irActividad(clase: Class<*>, idEquipo: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo) // pasar el ID como extra en el Intent
        startActivity(intent)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_VerJugadores -> {
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }

            R.id.mi_Editar -> {
                "Hacer algo con: ${idItemSeleccionado}"
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
        val id = info.position
        idItemSeleccionado = id
    }
}

