package com.serafine.room

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.serafine.room.userdatabase.AppDatabase
import com.serafine.room.userdatabase.User
import java.util.*

class EditorActivity : AppCompatActivity() {
    lateinit var nama: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var pickDate: Button
    lateinit var register: Button
    lateinit var date: TextView
    var dateString: String? = null

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        database = AppDatabase.getInstance(applicationContext)

        init()
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        pickDate.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    date.setText("" + mDay + "/" + mMonth + "/" + mYear)
                    dateString = "" + mDay + "/" + mMonth + "/" + mYear
                },
                year,
                month,
                day
            )
            dpd.show()
        }
        register.setOnClickListener {
            if (nama.text.toString().isEmpty() || email.text.toString()
                    .isEmpty() || password.text.toString().isEmpty() || dateString == null
            ) {
                Toast.makeText(
                    applicationContext,
                    "Pastikan tidak ada yang kosong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                database.userDao().insert(
                    User(
                        null,
                        nama.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        dateString!!
                    )
                )
                finish()
            }
        }
    }

    fun init(){
        nama = findViewById(R.id.nama)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        pickDate = findViewById(R.id.pickDate)
        register = findViewById(R.id.register)
        date = findViewById(R.id.date)
    }
}