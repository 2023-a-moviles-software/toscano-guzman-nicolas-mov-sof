package com.example.movilessoftware2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FReciclerViewAdaptadorNombreCedula(
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FReciclerViewAdaptadorNombreCedula.MyViewHolder> (){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionBoton: TextView
        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionBoton = view.findViewById(R.id.btn_dar_like)
            accionBoton.setOnClickListener { anadirLike() }
        }
        fun anadirLike(){
            numeroLikes = numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()
        }
    }

    // Setear el layout que vamos a usar
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // Setear los datos para la iteración
    // Setear datos iteracion al inicar el adaptador
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorEActual = this.lista[position]
        holder.nombreTextView.text = entrenadorEActual.nombre
        holder.cedulaTextView.text = entrenadorEActual.descripcion
        holder.accionBoton.text = "Like ${entrenadorEActual.id} - ${entrenadorEActual.nombre}"
        holder.likesTextView.text = "0"
    }

    // tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}