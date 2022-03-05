package com.example.firebaseteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    //Inicia um launcher Auth, ele é útil pois obriga você criar um resultado de autenticação
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
        this.onSignInResult(it)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createSignIntent()
    }
    //cria a Intent de login com seus icones
    fun createSignIntent(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AppleBuilder().build(),

            AuthUI.IdpConfig.EmailBuilder().build()
        )

        //cria e lança a intent com os icones
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    //Responsável por mostrar um resultado da tentativa de login
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult?) {
        val response = result?.idpResponse
        if (result != null) {
            if(result.resultCode == RESULT_OK){
                val user = FirebaseAuth.getInstance().currentUser
            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            }
        }
    }


}