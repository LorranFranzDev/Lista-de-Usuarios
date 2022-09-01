package dev.franz.listadeusurios.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.franz.listadeusurios.database.models.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT COUNT(uid) FROM user")
    fun getTotalItems() : Long
}