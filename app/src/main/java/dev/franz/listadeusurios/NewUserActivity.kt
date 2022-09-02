package dev.franz.listadeusurios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.franz.listadeusurios.database.AppDatabase
import dev.franz.listadeusurios.database.daos.UserDao
import dev.franz.listadeusurios.database.models.User
import dev.franz.listadeusurios.databinding.ActivityNewUserBinding
import kotlinx.coroutines.*

class NewUserActivity : AppCompatActivity() {


    private lateinit var binding: ActivityNewUserBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.database = AppDatabase.getInstance(this)

        this.userDao = this.database.userDao()

    }

    override fun onStart() {
        super.onStart()

        this.binding.btnSave.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {

                val result = saveUser(
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                    binding.edtProfessional.text.toString(),
                    binding.edtAge.text.toString()

                )

                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        this@NewUserActivity,
                        if (result) "Usuário salvo com Sucesso!" else "Erro ao salvar Usuário. Tente Novamente",
                        Toast.LENGTH_LONG
                    ).show()

                    if (result)
                        finish()

                }

            }

        }

    }

    private suspend fun saveUser(firstName: String, lastName: String, profession: String, age: String) : Boolean {

        if(firstName.isBlank() || firstName.isEmpty())
            return false

        if(lastName.isBlank() || lastName.isEmpty())
            return false

        if(profession.isBlank() || profession.isEmpty())
            return false

        if(age.isBlank() || age.isEmpty())
            return false

        this.userDao.insert(User(firstName, lastName, profession, age))

        return true

    }

}