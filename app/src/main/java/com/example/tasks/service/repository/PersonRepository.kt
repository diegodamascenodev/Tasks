package com.example.tasks.service.repository

import com.example.tasks.service.HeaderModel
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.repository.remote.PersonService
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

    private val mRemote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener) {

        val call: Call<HeaderModel> = mRemote.login(email, password)
        //Assíncrona
        call.enqueue(object : Callback<HeaderModel>{
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                response.body()?.let { listener.onSuccess(it) }                                     //Esse codigo verifica se o "response.body()" é nulo. Se não for o response.body() será passado como argumento no "listener.onSuccess()". Sim, o "it" está fazendo o papael do "response.body()". Era mais intuitivo se fizermos como foi feito na linha comentada abaixo. Mas quando colocamos desse jeito a própria IDE fala que esse valor pode ser nulo e te da a opção de transformar o código comentado abaixo no código usado nessa linha.
                //listener.onSuccess(response.body())
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure((t.message.toString()))
            }
        })
        //remote.login(email, password)
    }
}