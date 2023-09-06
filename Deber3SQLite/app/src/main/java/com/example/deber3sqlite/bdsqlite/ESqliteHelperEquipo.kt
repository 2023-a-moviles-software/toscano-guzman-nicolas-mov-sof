package com.example.deber3sqlite.bdsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.deber3sqlite.entidad.Equipo
import com.example.deber3sqlite.entidad.Jugador


class ESqliteHelperEquipo(
    contexto: Context? //this
) : SQLiteOpenHelper(
    contexto,
    "Equipos",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase) {
        val scriptSQLCrearTablaEquipos =
            """
                CREATE TABLE EQUIPO(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombreEquipo VARCHAR(50),
                    fundacionEquipo VARCHAR(50),
                    titulosEquipo VARCHAR(50),
                    ingresosEquipo VARCHAR(50)
                )
            """.trimIndent()
        db.execSQL(scriptSQLCrearTablaEquipos)
        //Tabla para
        val scriptSQLCrearTablaJugadores =
            """
                CREATE TABLE JUGADOR(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombreJugador VARCHAR(50),
                    casado VARCHAR(50),
                    edad VARCHAR(50),
                    altura VARCHAR(50),
                    posicion VARCHAR(50),
                    idEquipo VARCHAR(50),
                    FOREIGN KEY(idEquipo) REFERENCES EQUIPO(id) 
                )
            """.trimIndent()
        db.execSQL(scriptSQLCrearTablaJugadores)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun crearEquipo(
        nombreEquipo: String,
        fundacionEquipo: String,
        titulosEquipo: String,
        ingresosEquipo: String
    ): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreEquipo", nombreEquipo)
        valoresAGuardar.put("fundacionEquipo", fundacionEquipo)
        valoresAGuardar.put("titulosEquipo", titulosEquipo)
        valoresAGuardar.put("ingresosEquipo", ingresosEquipo)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "EQUIPO", //nombre tabla
                null,
                valoresAGuardar, //valores
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }

    fun crearJugador(
        nombreJugador: String,
        casadoJugador: String,
        edadJugador: Int,
        alturaJugador: Double,
        posicionJugador: String,
        idEquipo: Int
    ): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreJugador", nombreJugador)
        valoresAGuardar.put("casado", casadoJugador) // Aquí usamos el valor booleano directamente
        valoresAGuardar.put("edad", edadJugador)
        valoresAGuardar.put("altura", alturaJugador)
        valoresAGuardar.put("posicion", posicionJugador)
        valoresAGuardar.put("idEquipo", idEquipo)
        val resultadoGuardar = baseDatosEscritura.insert(
            "JUGADOR", //nombre tabla
            null,
            valoresAGuardar //valores
        )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }

    fun eliminarEquipoFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(id.toString())

        val resultadoEliminacion = conexionEscritura
            .delete(
                "EQUIPO", // Nombre tabla
                "id=?", //Consulta where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun eliminarJugadorFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(id.toString())

        val resultadoEliminacion = conexionEscritura
            .delete(
                "JUGADOR", // Nombre tabla
                "id=?", //Consulta where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun editarEquipoFormulario(
        nombreEquipo: String,
        fundacionEquipo: String,
        titulosEquipo: String,
        ingresosEquipo: String,
        id: Int,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreEquipo", nombreEquipo)
        valoresAActualizar.put("fundacionEquipo", fundacionEquipo)
        valoresAActualizar.put("titulosEquipo", titulosEquipo)
        valoresAActualizar.put("ingresosEquipo", ingresosEquipo)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "EQUIPO", //Nombre tabla
                valoresAActualizar,
                "id=?", //Consulta where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        mostrarEquipos()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun editarJugadorFormulario(
        nombreJugador: String,
        casadoJugador: String,
        edadJugador: String,
        alturaJugador: String,
        posicionJugador: String,
        id: Int,
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreJugador", nombreJugador)
        valoresAActualizar.put("casado", casadoJugador)
        valoresAActualizar.put("edad", edadJugador)
        valoresAActualizar.put("altura", alturaJugador)
        valoresAActualizar.put("posicion", posicionJugador)
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "JUGADOR",
                valoresAActualizar,
                "id=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun mostrarEquipos(): ArrayList<Equipo> {
        // on below line we are creating a
        // database for reading our database.
        val db = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorCourses = db.rawQuery("SELECT * FROM EQUIPO", null)

        // on below line we are creating a new array list.
        val listaDeEquipos: ArrayList<Equipo> = arrayListOf()

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                listaDeEquipos.add(
                    Equipo(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getInt(3),
                        cursorCourses.getDouble(4),
                        mostrarJugadores(cursorCourses.getInt(0))
                    )
                )
            } while (cursorCourses.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close()
        return listaDeEquipos
    }

    fun mostrarJugadores(idEquipo: Int): ArrayList<Jugador> {
        val db = this.readableDatabase
        val parametroDeConsulta = arrayOf(idEquipo.toString())
        val cursorCourses =
            db.rawQuery("SELECT * FROM JUGADOR ", null)
        val listaDeJugadores: ArrayList<Jugador> = arrayListOf()

        if (cursorCourses.moveToFirst()) {
            do {
                listaDeJugadores.add(
                    Jugador(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getInt(3),
                        cursorCourses.getDouble(4),
                        cursorCourses.getString(5)
                    )
                )
            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return listaDeJugadores
    }

}