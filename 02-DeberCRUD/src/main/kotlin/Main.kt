import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val gson = Gson()
val equipos = cargarDatosDeArchivoEquipos("src/main/kotlin/equipos.json")
val jugadores = cargarDatosDeArchivoJugadores("src/main/kotlin/jugadores.json")

data class EquipoFutbol(
    var nombreEquipo: String,
    var entrenador: String,
    var fundacion: Date,
    var titulosGanados: Int,
    var ingresosTotales: Double
)

data class Jugador(
    var nombreJugador: String,
    var casado: Boolean,
    var edad: Int,
    var altura: Double,
    var posicion: Char?,
    var equipoDelJugador: String?
)

fun main(args: Array<String>) {
    var opcion = 1
    while (opcion == 1) {
        val operacionCRUD = menu()
        //Crear un equipo
        if (operacionCRUD == 1) {
            val equipoCreado = crearEquipo()
            equipos.add(equipoCreado)
            guardarDatosEnArchivoEquipo(equipos, "src/main/kotlin/equipos.json")
        }
        //Crear un jugador
        if (operacionCRUD == 2) {
            val jugadorCreado = crearJugador()
            jugadores.add(jugadorCreado)
            guardarDatosEnArchivoJugador(jugadores, "src/main/kotlin/jugadores.json")
        }
        //Ver todos los equipos
        if (operacionCRUD == 3) {
            for (i in equipos) {
                println("----------------------------------")
                println("Nombre del equipo: ${i.nombreEquipo}")
                println("Entrenador: ${i.entrenador}")
                println("Fundación: ${i.fundacion}")
                println("Títulos ganados: ${i.titulosGanados}")
                println("Ingresos totales: ${i.ingresosTotales}")
                println("----------------------------------")
            }
        }
        //Ver todos los jugadores
        if (operacionCRUD == 4) {
            for (i in jugadores) {
                println("----------------------------------")
                println("Nombre: ${i.nombreJugador}")
                println("Casado: ${i.casado}")
                println("Edad: ${i.edad}")
                println("Altura: ${i.altura}")
                println("Posición: ${i.posicion}")
                println("Equipo del jugador: ${i.equipoDelJugador}")
                println("----------------------------------")
            }
        }
        if (operacionCRUD == 5) {
            println("¿Qué equipo quiere actualizar?")
            val nombreEquipo = readln()
            actualizarEquipo(nombreEquipo)
        }
        if (operacionCRUD == 6) {
            println("¿Qué jugador quiere actualizar?")
            val nombreJugador = readln()
            actualizarJugador(nombreJugador)
        }
        if (operacionCRUD == 7) {
            println("¿Qué equipo quiere borrar?")
            val nombreEquipo = readln()
            eliminarEquipo(nombreEquipo)
        }
        if (operacionCRUD == 8) {
            println("¿Qué jugador quiere borrar?")
            val nombreJugador = readln()
            eliminarJugador(nombreJugador)
        }

        if (operacionCRUD < 1 || operacionCRUD > 8) {
            println("Opción no válida")
        }

        println(
            "¿Quiere realizar otra operación?" +
                    "  1. Sí" +
                    "  2. No"
        )
        opcion = readln().toInt()
    }
}

fun menu(): Int {
    println("1. Crear Equipo")
    println("2. Crear Jugador")
    println("3. Ver Equipos")
    println("4. Ver Jugadores")
    println("5. Actualizar Equipo")
    println("6. Actualizar Jugador")
    println("7. Borrar Equipo")
    println("8. Borrar Jugador")
    println("¿Qué operación quiere realizar?")
    return readln().toInt()
}

fun cargarDatosDeArchivoEquipos(nombreArchivo: String): ArrayList<EquipoFutbol> {
    val archivo = File(nombreArchivo)
    if (archivo.exists()) {
        val json = archivo.readText()
        if (json.isNotEmpty()) {
            val equiposArray = gson.fromJson<List<EquipoFutbol>>(json, object : TypeToken<List<EquipoFutbol>>() {}.type)
            return ArrayList(equiposArray)
        }
    }
    return ArrayList()
}

fun cargarDatosDeArchivoJugadores(nombreArchivo: String): ArrayList<Jugador> {
    val archivo = File(nombreArchivo)
    if (archivo.exists()) {
        val json = archivo.readText()
        if (json.isNotEmpty()) {
            val jugadoresArray = gson.fromJson<List<Jugador>>(json, object : TypeToken<List<Jugador>>() {}.type)
            return ArrayList(jugadoresArray)
        }
    }
    return ArrayList()
}

fun guardarDatosEnArchivoEquipo(datos: ArrayList<EquipoFutbol>, rutaArchivo: String) {
    val gson = Gson()
    val json = gson.toJson(datos)
    File(rutaArchivo).writeText(json)
}

fun guardarDatosEnArchivoJugador(datos: ArrayList<Jugador>, rutaArchivo: String) {
    val gson = Gson()
    val json = gson.toJson(datos)
    File(rutaArchivo).writeText(json)
}

fun crearEquipo(): EquipoFutbol {
    println("¿Cuál es el nombre del equipo?")
    val nombreEquipo = readln()
    println("¿Cuál es el nombre del entrenador del equipo?")
    val entrenador = readln()
    println("¿Cuál es la fecha de su fundación del equipo?  'dd/MM/yyyy' ")
    val fechaStr = readlnOrNull()
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val fundacion: Date = formatoFecha.parse(fechaStr)
    println("¿Cuántos títulos ganados tiene el equipo?")
    val titulosGanados = readln().toInt()
    println("¿Cuáles son sus ingresos por temporada tiene el equipo?")
    val ingresosTotales = readln().toDouble()
    return EquipoFutbol(nombreEquipo, entrenador, fundacion, titulosGanados, ingresosTotales)
}

fun crearJugador(): Jugador {
    println("¿Cuál es el nombre del jugador?")
    val nombreJugador = readln()
    println("¿El jugador está casado? True o False")
    val casado = readln().toBoolean()
    println("¿Cuál es la edad del jugador?")
    val edad = readln().toInt()
    println("¿Cuál es la altura del jugador?")
    val altura = readln().toDouble()
    println("¿Cuál es la posicion del jugador?")
    val posicion = readln()[0]
    println("Ingrese el nombre del equipo al que desea agregar el jugador:")
    val nombreEquipo = readln()
    val equipoSeleccionado = equipos.find { it.nombreEquipo == nombreEquipo }
    return if (equipoSeleccionado != null) {
        val jugador = Jugador(nombreJugador, casado, edad, altura, posicion, nombreEquipo)
        guardarDatosEnArchivoJugador(jugadores, "src/main/kotlin/jugadores.json")
        println("El jugador ha sido agregado al equipo")
        jugador
    } else {
        println("No se encontró el equipo. El jugador se creará sin asignar un equipo.")
        val jugador = Jugador(nombreJugador, casado, edad, altura, posicion, "null")
        guardarDatosEnArchivoJugador(jugadores, "src/main/kotlin/jugadores.json")
        jugador
    }
}

fun actualizarEquipo(nombreEquipo: String) {
    val equipoSeleccionado = equipos.find { it.nombreEquipo == nombreEquipo }
    if (equipoSeleccionado != null) {
        println("Entrenador: ${equipoSeleccionado.entrenador}")
        println("Fundación: ${equipoSeleccionado.fundacion}")
        println("Títulos ganados: ${equipoSeleccionado.titulosGanados}")
        println("Ingresos totales: ${equipoSeleccionado.ingresosTotales}")
    } else {
        println("Equipo no encontrado")
    }
    println("\n¿Qué información quiere actualizar?")
    val infoActualizar = readln()

    if ((infoActualizar == "Entrenador")) {
        if (equipoSeleccionado != null) {
            println("¿Cuál es el nuevo nombre del entrenador del equipo?")
            val entrenadorActualizado = readln()
            equipoSeleccionado.entrenador = entrenadorActualizado
        }
    }
    if ((infoActualizar == "Fundación")) {
        if (equipoSeleccionado != null) {
            println("¿Cuál es la nueva fecha de su fundación del equipo?  'dd/MM/yyyy' ")
            val fechaStr = readlnOrNull()
            val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
            val fundacionActualizada: Date = formatoFecha.parse(fechaStr)
            equipoSeleccionado.fundacion = fundacionActualizada
        }
    }
    if ((infoActualizar == "Títulos ganados")) {
        if (equipoSeleccionado != null) {
            println("¿Cuántos títulos ganados tiene el equipo?")
            val titulosActualizado = readln().toInt()
            equipoSeleccionado.titulosGanados = titulosActualizado
        }
    }
    if ((infoActualizar == "Ingresos totales")) {
        if (equipoSeleccionado != null) {
            println("¿Cuáles son sus ingresos por temporada tiene el equipo?")
            val ingresosActualizados = readln().toDouble()
            equipoSeleccionado.ingresosTotales = ingresosActualizados
        }
    }
    guardarDatosEnArchivoEquipo(equipos, "src/main/kotlin/equipos.json")
    println("Equipo Actualizado\n")
}

fun actualizarJugador(nombreJugador: String) {
    val jugadorSeleccionado = jugadores.find { it.nombreJugador == nombreJugador }
    if (jugadorSeleccionado != null) {
        println("Casado: ${jugadorSeleccionado.casado}")
        println("Edad: ${jugadorSeleccionado.edad}")
        println("Altura: ${jugadorSeleccionado.altura}")
        println("Posición: ${jugadorSeleccionado.posicion}")
        println("Equipo del jugador: ${jugadorSeleccionado.equipoDelJugador}")
    } else {
        println("Jugador no encontrado")
    }
    println("\n¿Qué información quiere actualizar?")
    val infoActualizar = readln()

    if ((infoActualizar == "Casado")) {
        if (jugadorSeleccionado != null) {
            println("¿El jugador está casado? True o False")
            val casadoActualizado = readln().toBoolean()
            jugadorSeleccionado.casado = casadoActualizado
        }
    }
    if ((infoActualizar == "Edad")) {
        if (jugadorSeleccionado != null) {
            println("¿Cuál es la edad del jugador?")
            val edadActualizado = readln().toInt()
            jugadorSeleccionado.edad = edadActualizado
        }
    }
    if ((infoActualizar == "Altura")) {
        if (jugadorSeleccionado != null) {
            println("¿Cuál es la altura del jugador?")
            val alturaActualizado = readln().toDouble()
            jugadorSeleccionado.altura = alturaActualizado
        }
    }
    if ((infoActualizar == "Posicion")) {
        if (jugadorSeleccionado != null) {
            println("¿Cuál es la posicion del jugador?")
            val posicionActualizado = readln()[0]
            jugadorSeleccionado.posicion = posicionActualizado
        }
    }
    if (infoActualizar == "Equipo del jugador") {
        println("¿Cuál es el nuevo equipo del jugador?")
        val equipoActualizado = readln()
        val equipoSeleccionado = equipos.find { it.nombreEquipo == equipoActualizado }
        if (equipoSeleccionado != null) {
            if (jugadorSeleccionado != null) {
                jugadorSeleccionado.equipoDelJugador = equipoActualizado
            }
        } else {
            println("No se encontró el equipo. El jugador se creará sin asignar un equipo.")
            if (jugadorSeleccionado != null) {
                jugadorSeleccionado.equipoDelJugador = "null"
            }
        }
    }
    guardarDatosEnArchivoJugador(jugadores, "src/main/kotlin/jugadores.json")
    println("Jugador Actualizado\n")
}

fun eliminarJugador(nombreJugador: String) {
    val jugadorAEliminar = jugadores.find { it.nombreJugador == nombreJugador }
    if (jugadorAEliminar != null) {
        jugadores.remove(jugadorAEliminar)
        guardarDatosEnArchivoJugador(jugadores, "src/main/kotlin/jugadores.json")
        println("Jugador eliminado")
    } else {
        println("Jugador no eliminado")
    }
}

fun eliminarEquipo(nombreEquipo: String) {
    val equipoAEliminar = equipos.find { it.nombreEquipo == nombreEquipo }
    if (equipoAEliminar != null) {
        val jugadoresDelEquipoAEliminar = jugadores.filter { it.equipoDelJugador == nombreEquipo }
        jugadoresDelEquipoAEliminar.forEach { it.equipoDelJugador = "null" }
        equipos.remove(equipoAEliminar)


        guardarDatosEnArchivoEquipo(equipos, "src/main/kotlin/equipos.json")
        guardarDatosEnArchivoJugador(jugadores,"src/main/kotlin/jugadores.json")
        println("Equipo eliminado")
    } else {
        println("Equipo no eliminado")
    }
}