package com.example.appfilmes.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appfilmes.R
import com.example.appfilmes.adapter.AdapterCategoria
import com.example.appfilmes.api.Api
import com.example.appfilmes.databinding.ActivityTelaPrincipalBinding
import com.example.appfilmes.model.Categoria
import com.example.appfilmes.model.Filme
import com.example.appfilmes.model.categorias
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    private lateinit var adapterCategoria: AdapterCategoria
    private val listaCategorias: MutableList<Categoria> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder a barra de suporte
        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")

        //Recycler View

        val recyclerViewFilmes = binding.recyclerViewFilmes
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        recyclerViewFilmes.setHasFixedSize(true)
        adapterCategoria = AdapterCategoria(this, listaCategorias)
        recyclerViewFilmes.adapter = adapterCategoria



        binding.tvSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Usuário deslogado", Toast.LENGTH_SHORT).show()
        }
//Configurando Retrofit

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(Api::class.java)
        retrofit.listaCategoria().enqueue(object : Callback<categorias>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<categorias>, response: Response<categorias>) {
             if(response.code()==200){
                 response.body()?.let {
                     adapterCategoria.listaCategoria.addAll(it.categorias)
                     adapterCategoria.notifyDataSetChanged()
                     binding.containerProgressBar.visibility = View.GONE
                     binding.progressBar.visibility = View.GONE
                     binding.tvCarregando.visibility = View.GONE
                 }
             }
            }

            override fun onFailure(call: Call<categorias>, t: Throwable) {
               Toast.makeText(applicationContext,"Erro ao buscar todos os filmes!",Toast.LENGTH_SHORT).show()
            }

        })
    }
}

