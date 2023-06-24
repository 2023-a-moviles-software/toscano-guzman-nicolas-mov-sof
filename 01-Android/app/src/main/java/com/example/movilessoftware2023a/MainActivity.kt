package com.example.movilessoftware2023a

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    "${data?.getStringExtra("nombreModificado")}"
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonCicloVida = findViewById<Button>(
            R.id.btn_ciclo_vida
        )
        botonCicloVida.setOnClickListener {
            irActividad(AACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(
            R.id.btn_ir_list_view
        )
        botonCicloVida.setOnClickListener {
            irActividad(BListView::class.java)
        }
    }


    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        //NO RECIBIMOS RESPUESTA
        startActivity(intent)
        //this.startActivity
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros
        //aceptarmos primitivas
        intentExplicito.putExtra("nombre","Nicolás")
        intentExplicito.putExtra("apellido","Toscano")
        intentExplicito.putExtra("edad",23)
        //enviamos el intent con RESPUESTA
        //RECIBIMOS INTENT
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }
}