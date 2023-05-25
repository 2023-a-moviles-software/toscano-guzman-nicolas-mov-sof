import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    //Inmutables (No se reasignan "=")
    val inmutable: String = "Nicolás";
    //inmutable = "Toscano";


    //Mutables (Re asignar)
    var mutable: String = "Toscano";
    mutable = "Nicolás";

    //val > var
    //Siempre usar val

    //Duck Typing
    var ejemploVariable = "Nicolás Toscano";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim()
    //ejemplo = edadEjemplos;


    //Valor primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayoredad: Boolean = true
    //Clases Java
    val fechaInicio: Date = Date()

    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.0)
    calcularSueldo(10.00, 12.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00) //Paramateros nombrados
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //Parametros nombrados
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {//Bloque de codigo del cosntructor
        this.numeroUno = uno
        numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(
//Constructor PRIMARIO
    // Ejemplo:
    // uno: Int, (Parametro (sin modificador de acceso))
    // private var uno: Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
    // public var uno: Int,
    protected val numeroUno: Int, // Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, // Propiedad de la clase protected numeros.numeroDos
) {
    // var cedula: string = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos; // this es opcional
        numeroUno; numeroDos; // sin el "this", es lo mismo
        println("Inicializando")
    }
}


//void -> Unit

fun imprimirNombre(nombre: String): Unit {
    println("Nombre : ${nombre}") //template strings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.0, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable  puede ser nula
): Double {
    //Int -> Int (nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}
