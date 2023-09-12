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
//        crearDatosPrueba()
//        consultarIndiceCompuesto(adaptador)

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = adaptador.getItem(position)
                val idEquipo = selectedItem?.id
                if (idEquipo != null) {
                    irActividadConParametros(VistaJugador::class.java, idEquipo)
                }
            }

        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_equipo)
        botonCrearEquipo.setOnClickListener {
            irActividad(CrearEquipo::class.java)
        }

        registerForContextMenu(listView)

        adaptador.notifyDataSetChanged()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConParametros(clase: Class<*>, idEquipo: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo) // Pasa el ID del equipo a la actividad VistaJugador
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_Editar -> {
                return true
            }

            R.id.mi_Eliminar -> {
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

    }

    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<Equipo>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("equipos")
        limpiarArreglo()
        citiesRefUnico
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloEquipo(ciudad)
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

    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val equipos = db.collection("equipos")

        var id = generateUniqueNumericId()

        val data1 = hashMapOf(
            "id" to id,
            "nombre" to "Real Madrid",
            "fundacion" to "1902",
            "trofeos" to 34,
            "ingresos" to 123.233,
            "jugadores" to listOf("Cristiano Ronaldo", "Sergio Ramos"),
        )
        equipos.document("RM").set(data1)
//        equipos.document(id.toString()).set(data1)

        id = generateUniqueNumericId()
        val data2 = hashMapOf(
            "id" to id,
            "nombre" to "Barcelona",
            "fundacion" to "1945",
            "trofeos" to 34,
            "ingresos" to 123.233,
            "jugadores" to listOf("messi", "pique"),
        )
        equipos.document("FCB").set(data2)
//        equipos.document(id.toString()).set(data2)

        id = generateUniqueNumericId()
        val data3 = hashMapOf(
            "id" to id,
            "nombre" to "Liverpool",
            "fundacion" to "1945",
            "trofeos" to 34,
            "ingresos" to 123.233,
            "jugadores" to listOf("messi", "pique"),
        )
        equipos.document("LV").set(data3)
//        equipos.document(id.toString()).set(data3)

        id = generateUniqueNumericId()
        val data4 = hashMapOf(
            "id" to id,
            "nombre" to "Manchester",
            "fundacion" to "1945",
            "trofeos" to 34,
            "ingresos" to 123.233,
            "jugadores" to listOf("messi", "pique"),
        )
        equipos.document("MC").set(data4)
//        equipos.document(id.toString()).set(data4)
    }

    var id = 0
    fun generateUniqueNumericId(): Int {
        id++
        return id
    }

    override fun onResume() {
        super.onResume()
        consultarIndiceCompuesto(adaptador)
        limpiarArreglo()
    }

}

