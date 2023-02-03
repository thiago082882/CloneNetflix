package com.example.appfilmes.view

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.appfilmes.R
import com.example.appfilmes.databinding.ActivityDetalhesFilmesBinding

class DetalhesFilmes : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmesBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder a barra de suporte
        supportActionBar!!.hide()
        window.statusBarColor= Color.parseColor("#000000")

        val capa = intent.extras?.getString("capa")
        val nome = intent.extras?.getString("nome")
        val descricao = intent.extras?.getString("descricao")
        val elenco = intent.extras?.getString("elenco")

        Glide.with(this).load(capa).centerCrop().into(binding.capaFilme)
        binding.tvNome.setText(nome)
        binding.tvDescricao.setText("Descrição: $descricao")
        binding.tvElenco.setText("Elenco: $elenco")
    }
}