package com.example.examen2firebase

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VistaJugador : AppCompatActivity() {

//    val arreglo: ArrayList<Jugador> = arrayListOf()
//    private lateinit var adaptador: ArrayAdapter<Jugador>
//
//    var idEquipo = -1
//    var nombreEquipo: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_vista_jugador)
//
//        idEquipo = intent.getIntExtra("idEquipo", -1)
//        nombreEquipo = intent.getStringExtra("nombreEquipo")
//
//        val listView = findViewById<ListView>(R.id.lista_jugadores)
//
//        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipo)
//        tituloEquipoEditar.text = nombreEquipo
//
//        adaptador = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            arreglo
//        )
//
//        listView.adapter = adaptador
//
//        val botonCrearEquipo = findViewById<Button>(R.id.btn_crear_jugador)
//        botonCrearEquipo.setOnClickListener {
//            irActividadConParametros(CrearJugador::class.java, idEquipo, nombreEquipo)
//        }
//
//        registerForContextMenu(listView)
//
//        adaptador.notifyDataSetChanged()
//    }
//
//    fun irActividadConParametros(clase: Class<*>, idEquipo: Int, nombreEquipo: String?) {
//        val intent = Intent(this, clase)
//        intent.putExtra("idEquipo", idEquipo)
//        intent.putExtra("nombreEquipo", nombreEquipo)
//        startActivity(intent)
//    }
//
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.mi_Editar -> {
//                return true
//            }
//
//            R.id.mi_Eliminar -> {
//                return true
//            }
//
//            else -> super.onContextItemSelected(item)
//
//        }
//    }
//
//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//    }
//
//    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<Jugador>) {
//        val db = Firebase.firestore
//        val jugadoresRefUnico = db.collection("jugadores")
//        limpiarArreglo()
//        jugadoresRefUnico
//            .get()
//            .addOnSuccessListener {
//                for (jugador in it) {
//                    anadirAArregloJugador(jugador)
//                }
//                adaptador.notifyDataSetChanged()
//            }
//            .addOnFailureListener { }
//    }
//
//    private fun anadirAArregloJugador(
//        jugador: QueryDocumentSnapshot
//    ) {
//        val id = jugador.data["id"]?.let { (it as? Long)?.toInt() }
//        val nombre = jugador.data["nombre"] as String?
//        val casado = jugador.data["casado"] as Boolean?
//        val edad = jugador.data["edad"]?.let { (it as? Long)?.toInt() }
//        val altura = jugador.data["altura"] as Double?
//        val posicion = jugador.data["posicion"] as String?
//        val equipo = jugador.data["equipo"] as String?
//
//        val nuevoJugador = Jugador(id, nombre, casado, edad, altura, posicion, equipo)
//        arreglo.add(nuevoJugador)
//    }
//
//    private fun limpiarArreglo() {
//        arreglo.clear()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        consultarIndiceCompuesto(adaptador)
//        limpiarArreglo()
//    }
//
//
//}

    val arreglo: ArrayList<Jugador> = arrayListOf()
    private lateinit var adaptador: ArrayAdapter<Jugador>

    var idEquipo = -1
    var nombreEquipo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_jugador)

        idEquipo = intent.getIntExtra("idEquipo", -1)
        nombreEquipo = intent.getStringExtra("nombreEquipo")

        val listView = findViewById<ListView>(R.id.lista_jugadores)

        val tituloEquipoEditar = findViewById<TextView>(R.id.tituloEquipo)
        tituloEquipoEditar.text = nombreEquipo

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )

        listView.adapter = adaptador

        val botonCrearJugador = findViewById<Button>(R.id.btn_crear_jugador)
        botonCrearJugador.setOnClickListener {
            irActividad(CrearJugador::class.java)
        }

        registerForContextMenu(listView)

        adaptador.notifyDataSetChanged()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
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
    }

    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<Jugador>) {
        val db = Firebase.firestore
        val jugadoresRefUnico = db.collection("jugadores")
        limpiarArreglo()
        jugadoresRefUnico
            .get()
            .addOnSuccessListener {
                for (jugador in it) {
                    anadirAArregloJugador(jugador)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }

    private fun anadirAArregloJugador(
        jugador: QueryDocumentSnapshot
    ) {
        val id = jugador.data["id"]?.let { (it as? Long)?.toInt() }
        val nombre = jugador.data["nombreJugador"] as String?
        val casado = jugador.data["casado"] as Boolean?
        val edad = jugador.data["edad"]?.let { (it as? Long)?.toInt() }
        val altura = jugador.data["altura"] as Double?
        val posicion = jugador.data["posicion"] as String?
        val equipo = jugador.data["equipoDelJugador"] as String?

        val nuevoJugador = Jugador(id, nombre, casado, edad, altura, posicion, equipo)
        arreglo.add(nuevoJugador)
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
