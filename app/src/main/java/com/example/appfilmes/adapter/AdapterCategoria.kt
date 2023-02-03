package com.example.appfilmes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfilmes.databinding.CategoriaItemBinding
import com.example.appfilmes.model.Categoria
import com.example.appfilmes.model.Filme

class AdapterCategoria(private val context:Context,val listaCategoria:MutableList<Categoria>):
    RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context),parent,false)
   return CategoriaViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
       holder.titulo.text = listaCategoria[position].titulo
        val categoria = listaCategoria[position]
        holder.recyclerViewFilmes.adapter = AdapterFilmes(context,categoria.filmes)
        holder.recyclerViewFilmes.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    override fun getItemCount() = listaCategoria.size

    inner class CategoriaViewHolder(binding: CategoriaItemBinding):RecyclerView.ViewHolder(binding.root){
   val titulo =binding.tvTitulo
        val recyclerViewFilmes = binding.recyclerViewFilmes
    }

}