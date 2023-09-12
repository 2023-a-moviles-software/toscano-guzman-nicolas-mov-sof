package com.example.examen2firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class IFirestore : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<Equipo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun crearEquipo(
        equipo: Equipo,
        adaptador: ArrayAdapter<Equipo>
    ) {
        val db = Firebase.firestore
        val equiposRef = db.collection("equipos")

        equiposRef
            .add(equipo)
            .addOnSuccessListener { documentReference ->
                equipo.id = documentReference.id.toInt()
                arreglo.add(equipo)
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                print(e)
            }
    }


    fun consultarEquipos(
        adaptador: ArrayAdapter<Equipo>
    ) {
        val db = Firebase.firestore
        val citiesRef = db.collection("equipos")
//            .orderBy("population")
            .limit(1)
        val tarea: Task<QuerySnapshot>?
        if (query == null) {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            tarea = citiesRef.get()
        } else {
            tarea = query!!.get()
        }
        if (tarea != null) {
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots) {
                        anadirAArregloCiudad(ciudad)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener { }
        }
    }

    fun guardarQuery(
        documentSnapshot: QuerySnapshot,
        refEquipos: Query
    ) {
        if (documentSnapshot.size() > 0) {
            val ultimoDocumento = documentSnapshot
                .documents[documentSnapshot.size() - 1]
            query = refEquipos
                .startAfter(ultimoDocumento)
        }
    }

    fun eliminarEquipos(id: String) {
        val db = Firebase.firestore
        val referenciaEquipos = db.collection("equipos")
        referenciaEquipos
            .document(id) // Aquí debes pasar el ID del documento que deseas eliminar
            .delete()
            .addOnSuccessListener {
                // La eliminación se completó con éxito
                // Puedes realizar acciones adicionales aquí si es necesario
            }
            .addOnFailureListener { e ->
                // Manejar errores si la eliminación falla
                // e.printStackTrace()
            }
    }

    fun crearEjemplo() {
        val db = Firebase.firestore
        val referenciaEjemploEstudiante = db
            .collection("ejemplo")
        // .document("id_hijo")
        // .collection("estudiante")
        val identificador = Date().time
        val datosEstudiante = hashMapOf(
            "nombre" to "Adrian",
            "graduado" to false,
            "promedio" to 14.00,
            "direccion" to hashMapOf(
                "direccion" to "Mitad del mundo",
                "numeroCalle" to 1234
            ),
            "materias" to listOf("web", "moviles")
        )


        // identificador quemado (crear/actualizar)
        referenciaEjemploEstudiante
            .document("12345678")
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        // identificador quemado pero autogenerado con Date().time
        referenciaEjemploEstudiante // (crear/actualizar)
            .document(identificador.toString())
            .set(datosEstudiante)
            .addOnSuccessListener { }
            .addOnFailureListener { }
        // Sin IDENTIFICADOR (crear)
        referenciaEjemploEstudiante
            .add(datosEstudiante)
            .addOnCompleteListener { }
            .addOnFailureListener { }
    }


    fun consultarIndiceCompuesto(adaptador: ArrayAdapter<Equipo>) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("equipos")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        citiesRefUnico
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population", 4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it) {
                    anadirAArregloCiudad(ciudad)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener { }

    }

    fun consultarDocumento(
        adaptador: ArrayAdapter<Equipo>
    ) {
        val db = Firebase.firestore
        val citiesRefUnico = db.collection("cities")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        // Coleccion "ciudad"
        //     -> Coleccion "barrio"
        //            -> Coleccion "direccion[
        // "Quito" => "La_Floresta" => "E90-001"
        // db.collection("ciudad").document("Quito")
        //   .collection("barrio").document("La Floresta").collection("direccion")
        //   .document("E90-001")
        // .collection("nombre_coleccion_hijo").document("id_hijo")
        // .collection("nombre_coleccion_nieto").document("id_nieto")
        citiesRefUnico
            .document("BJ")
            .get() // obtener 1 DOCUMENTO
            .addOnSuccessListener {
                // it=> ES UN OBJETO!
                arreglo
                    .add(
                        Equipo(
                            it.data?.get("id") as Int,
                            it.data?.get("nombreEquipo") as String?,
                            it.data?.get("fundacion") as String?,
                            it.data?.get("titulosGanados") as Int?,
                            it.data?.get("ingresosTotales") as Double?,
                            it.data?.get("jugadorObtenido") as MutableList<Jugador>,
                        )
                    )
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                // salio Mal
            }
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun anadirAArregloCiudad(
        equipo: QueryDocumentSnapshot
    ) {
        // ciudad.id
        val nuevaCiudad = Equipo(
            equipo.data["id"] as Int,
            equipo.data["nombreEquipo"] as String?,
            equipo.data["fundacion"] as String?,
            equipo.data["titulosGanados"] as Int?,
            equipo.data["ingresosTotales"] as Double?,
            equipo.data["jugadorObtenido"] as MutableList<Jugador>,
        )
        arreglo.add(nuevaCiudad)
    }

}