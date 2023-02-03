package com.example.appfilmes.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appfilmes.databinding.ActivityFormLoginBinding
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {

    private lateinit var binding:ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Esconder a barra de suporte
        supportActionBar!!.hide()
        window.statusBarColor= Color.parseColor("#000000")

        //Aparecer o Cursor na caixa de texto

        binding.edtEmail.requestFocus()

    binding.btEntrar.setOnClickListener{

        val email = binding.edtEmail.text.toString()
        val senha = binding.edtSenha.text.toString()

        when {
            email.isEmpty()->{
                binding.containerEmail.helperText = "Preencha o email!"
                binding.containerEmail.boxStrokeColor=Color.parseColor("#FF9800")
            }
            senha.isEmpty()->{
                binding.containerSenha.helperText="Preencha a senha!"
                binding.containerSenha.boxStrokeColor=Color.parseColor("#FF9800")
            }
            else -> {
                autenticacao(email,senha)
            }
        }


    }
 binding.tvTelaCadastro.setOnClickListener {

     val intent = Intent(this,FormCadastro::class.java)
     startActivity(intent)

 }

    }

    private fun autenticacao(email: String, senha: String) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {autenticacao->
            if(autenticacao.isSuccessful){
                Toast.makeText(this,"Login Efetuado com sucesso!",Toast.LENGTH_SHORT).show()
                 navegarTelaPrincipal()
            }


        }.addOnFailureListener {
            Toast.makeText(this,"Erro ao fazer o login do usuário!",Toast.LENGTH_SHORT).show()

        }

    }
    private  fun navegarTelaPrincipal(){
        val intent = Intent(this,TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if(usuarioAtual != null){
            navegarTelaPrincipal()

        }
    }
}