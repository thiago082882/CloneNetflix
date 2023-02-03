package com.example.appfilmes.api

import com.example.appfilmes.model.categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/filmes")
    fun listaCategoria():Call<categorias>
}