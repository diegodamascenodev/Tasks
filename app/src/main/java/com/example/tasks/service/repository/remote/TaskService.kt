package com.example.tasks.service.repository.remote

import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    //LISTAR TODAS AS TAREFAS SEM FILTRO
    @GET("Task")
    fun all(): Call<List<TaskModel>>


    //LISTA TODAS AS TAREFAS DOS PROXIMOS 7 DIAS
    @GET("Task/Next7Days")
    fun nextWeek(): Call<List<TaskModel>>


    //LISTA TODAS AS TAREFAS EXPIRADAS
    @GET("Task/Overdue")
    fun overdue(): Call<List<TaskModel>>


    //OBTEM TAREFAS DE ACORDO COM A QUANTIDADE DESEJADA
    @GET("Task/{id}")                                                                         //Esse id entre chaves é dinâmico. Ele pode variar. Ela indica o numero de tarefas que será obtida pela API. Se o id for tiver valor 5, por exemplo, ele obtém 5 tarefas na API.
    fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>                          //O "@Path" e seus parametros antes da variavel "id" indicam que é essa variavel que determinará o valor do "id" da URL na linha acima. O parâmetro encode codifica os possíveis espaços que podem existir no valor da variável. Já que não a conhecemos precisamos nos prevenir.


    //INSERIR TAREFA
    @POST("Task")
    @FormUrlEncoded
    fun create(
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("duaDate") duaDate: String,
        @Field("Complete") complete: Boolean
    ): Call<Boolean>


    //ATUALIZAR TAREFA
    @HTTP(method = "PUT", path = "Task", hasBody = true)                                            //Quando for metodo "PUT" e precisarmos passar valores no PUT, temos que usar a notação "@HTTP". Se não precisa-se passar valores aó poderiamos usar a notação "@PUT". O parametro "hasBody" indica que a requisição tem corpo, que recebe parâmetros.
    @FormUrlEncoded
    fun update(
        @Field("id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("duaDate") duaDate: String,
        @Field("Complete") complete: Boolean
    ): Call<Boolean>


    //DELETAR TAREFA
    @HTTP(method = "DELETE", path = "Task", hasBody = true)                                         //Quando for metodo "DELETE" e precisarmos passar valores no DELETE, temos que usar a notação "@HTTP". Se não precisa-se passar valores aó poderiamos usar a notação "@DELETE". O parametro "hasBody" indica que a requisição tem corpo, que recebe parâmetros.
    @FormUrlEncoded
    fun delete(
        @Field("id") id: Int
    ): Call<Boolean>


    //MARCA A TAREFA COMO COMPLETA
    @HTTP(method = "PUT", path = "Task/Complete", hasBody = true)                                   //Quando for metodo "PUT" e precisarmos passar valores no PUT, temos que usar a notação "@HTTP". Se não precisa-se passar valores aó poderiamos usar a notação "@PUT". O parametro "hasBody" indica que a requisição tem corpo, que recebe parâmetros.
    @FormUrlEncoded
    fun complete(
        @Field("id") id: Int
    ): Call<Boolean>


    //MARCA A TAREFA COMO INCOMPLETA
    @HTTP(method = "PUT", path = "Task/Undo", hasBody = true)                                       //Quando for metodo "PUT" e precisarmos passar valores no PUT, temos que usar a notação "@HTTP". Se não precisa-se passar valores aó poderiamos usar a notação "@PUT". O parametro "hasBody" indica que a requisição tem corpo, que recebe parâmetros.
    @FormUrlEncoded
    fun undo(
        @Field("id") id: Int
    ): Call<Boolean>
}