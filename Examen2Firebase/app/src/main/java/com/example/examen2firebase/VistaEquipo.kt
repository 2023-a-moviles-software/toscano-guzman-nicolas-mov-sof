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
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VistaEquipo : AppCompatActivity() {

    val arreglo: ArrayList<Equipo> = arrayListOf()
    private lateinit var adaptador: ArrayAdapter<Equipo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_equipo)

        val listView = findViewById<ListView>(R.id.listView_Equipo)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = adaptador.getItem(position)
                val idEquipo = selectedItem?.id
                val nombreEquipo =
                    selectedItem?.nombreEquipo // Obtén el nombre del equipo seleccionado
                if (idEquipo != null && nombreEquipo != null) {
                    val intent = Intent(this, VistaJugador::class.java)
                    intent.putExtra("idEquipo", idEquipo)
                    intent.putExtra("nombreEquipo", nombreEquipo) // Pasa el nombre del equipo
                    startActivity(intent)
                }
            }

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_jugador)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearEquipo::class.java)
        }

        registerForContextMenu(listView)

        adaptador.notifyDataSetChanged()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_Editar -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val equipoSeleccionado = adaptador.getItem(info.position)
                val intent = Intent(this, EditarEquipo::class.java)
                intent.putExtra("equipoId", equipoSeleccionado?.id)
                intent.putExtra("nombreEquipo", equipoSeleccionado?.nombreEquipo)
                intent.putExtra("fundacionEquipo", equipoSeleccionado?.fundacion)
                intent.putExtra("trofeosEquipo", equipoSeleccionado?.titulosGanados)
                intent.putExtra("ingresosEquipo", equipoSeleccionado?.ingresosTotales)
                startActivity(intent)
                return true
            }

            R.id.mi_Eliminar -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val equipoSeleccionado = adaptador.getItem(info.position)
                val db = Firebase.firestore
                val referenciaEquipos = db.collection("equipos")

                referenciaEquipos.document(equipoSeleccionado?.id.toString())
                    .delete()
                    .addOnSuccessListener {
                        onResume()
                    }
                    .addOnFailureListener { e ->
                    }

                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<Equipo>) {
        val db = Firebase.firestore
        val equiposRefUnico = db.collection("equipos")
        limpiarArreglo()
        equiposRefUnico
            .get()
            .addOnSuccessListener {
                for (equipo in it) {
                    anadirAArregloEquipo(equipo)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }

    private fun anadirAArregloEquipo(
        equipo: QueryDocumentSnapshot
    ) {
        val id = equipo.data["id"]?.let { (it as? Long)?.toInt() }
        val nombre = equipo.data["nombre"] as String?
        val fundacion = equipo.data["fundacion"] as String?
        val trofeos = equipo.data["trofeos"]?.let { (it as? Long)?.toInt() }
        val ingresos = equipo.data["ingresos"] as Double?
        val jugadores = equipo.data["jugadores"] as MutableList<Jugador>


        val nuevoEquipo = Equipo(id, nombre, fundacion, trofeos, ingresos, jugadores)
        arreglo.add(nuevoEquipo)
    }

    private fun limpiarArreglo() {
        arreglo.clear()
    }

    override fun onResume() {
        super.onResume()
        consultarIndiceCompuesto(adaptador)
        limpiarArreglo()
    }

}

