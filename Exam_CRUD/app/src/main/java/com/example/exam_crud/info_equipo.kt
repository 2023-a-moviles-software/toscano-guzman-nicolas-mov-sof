package com.example.exam_crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class info_equipo : AppCompatActivity() {

    private var equipoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_equipo)

        equipoId = intent.getIntExtra("idEquipo", 0)

        val listView2 = findViewById<ListView>(R.id.listView_equipo_completo)

        val equipo = BaseDeDatos.arregloEquipo.find { it.id == equipoId }

        if (equipo != null) {
            val adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                // Convertir el equipo a una lista para el adaptador
                listOf(equipo.toString())
            )
            listView2.adapter = adaptador
            adaptador.notifyDataSetChanged()
        }

        registerForContextMenu(listView2)
    }
}