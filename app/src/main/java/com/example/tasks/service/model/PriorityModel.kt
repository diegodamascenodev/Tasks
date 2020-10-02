package com.example.tasks.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "priority")
class PriorityModel {

    @SerializedName("Id")
    @ColumnInfo(name = "id")
    @PrimaryKey                                                                                     //Não marquei com auto generate pq esse ID vem da API. Ele não é gerado automaticamente pelo banco.
    var id: Int = 0

    @SerializedName("Description")
    @ColumnInfo(name = "description")
    var description: String = ""
}