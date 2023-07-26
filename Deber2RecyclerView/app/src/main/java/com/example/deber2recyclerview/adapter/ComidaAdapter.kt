package com.example.deber2recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber2recyclerview.entidad.Comida
import com.example.deber2recyclerview.R

class ComidaAdapter(private val listaComidas: List<Comida>) :
    RecyclerView.Adapter<ComidaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaViewHolder {
        //Regresa un view holder por cada adapter
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComidaViewHolder(layoutInflater.inflate(R.layout.item_comida, parent, false))
    }

    override fun onBindViewHolder(holder: ComidaViewHolder, position: Int) {
        val item = listaComidas[position]
        holder.constructor(item)
    }

    override fun getItemCount(): Int {
        return listaComidas.size
    }


}