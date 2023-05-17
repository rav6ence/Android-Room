package com.serafine.room

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serafine.room.adapter.UserAdapter
import com.serafine.room.userdatabase.AppDatabase
import com.serafine.room.userdatabase.User

class ListActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var resultText: TextView
    private var list = mutableListOf<User>()
    lateinit var adapter: UserAdapter
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recycler)
        resultText = findViewById(R.id.resultText)

        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val nama = intent.getStringExtra("nama")
        resultText.text = "Selamat Datang " + nama
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }
}