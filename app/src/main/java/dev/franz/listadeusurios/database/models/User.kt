package dev.franz.listadeusurios.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "first_name") val firsName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val profession: String,
    val age: String
){
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0

}
