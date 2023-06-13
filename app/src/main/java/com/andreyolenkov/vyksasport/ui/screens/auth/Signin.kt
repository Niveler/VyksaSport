package com.andreyolenkov.vyksasport.ui.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andreyolenkov.vyksasport.APP
import com.andreyolenkov.vyksasport.R
import com.andreyolenkov.vyksasport.REPOSITORY_PERSONS
import com.andreyolenkov.vyksasport.databinding.FragmentSigninBinding
import com.andreyolenkov.vyksasport.retrofit.AuthRequest
import com.andreyolenkov.vyksasport.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Signin : Fragment() {
    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSigninBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth()
        onClickRegister()
    }
    private fun auth() {
        val intercepter = HttpLoggingInterceptor()
        intercepter.level = HttpLoggingInterceptor.Level.BODY //перехватываем тело запроса

        val client = OkHttpClient.Builder().addInterceptor(intercepter).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://localhost")
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        val mainApi = retrofit.create(MainApi::class.java)

        binding.btSingin.setOnClickListener {
            /*CoroutineScope(Dispatchers.IO).launch {
                val user = mainApi.auth(
                    AuthRequest(
                        binding.enterLogin.text.toString(),
                        binding.enterPass.text.toString()
                    )
                )
                if (user.token != ""){
                    APP.navController.navigate(R.id.listComplex)
                } else {
                    Toast.makeText(context,"Bad request",Toast.LENGTH_LONG).show()
                }
            }*/
            //ЗАГРУШКА
            if (binding.enterLogin.text.toString()=="admin" && binding.enterPass.text.toString()=="admin") {
                val person = REPOSITORY_PERSONS.allUsers
            } else if (binding.enterLogin.text.toString()=="trainer" && binding.enterPass.text.toString()=="trainer") {

            } else if (binding.enterLogin.text.toString()=="user" && binding.enterPass.text.toString()=="user") {

            }
        }
    }
    fun onClickRegister() {
        binding.btRegister.setOnClickListener {
            APP.navController.navigate(R.id.action_signin_to_register)
        }
    }
}