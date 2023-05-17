package com.serafine.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.serafine.room.userdatabase.AppDatabase
import com.serafine.room.userdatabase.User

class MainActivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit var login: Button

    lateinit var email: EditText
    lateinit var pass: EditText

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        login = findViewById(R.id.login)
        email = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        database = AppDatabase.getInstance(applicationContext)

        fab.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, EditorActivity::class.java)
            startActivity(moveIntent)
        }
        login.setOnClickListener {
            if(email.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()){
                var list: List<User> = database.userDao().getUser(email.text.toString(), pass.text.toString())
                if(list.size > 0){
                    val moveIntent = Intent(this@MainActivity, ListActivity::class.java)
                    moveIntent.putExtra("nama", list[0].nama)
                    startActivity(moveIntent)
                }
                else{
                    Toast.makeText(applicationContext, "There is something wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}