package com.serafine.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serafine.room.R
import com.serafine.room.userdatabase.User

class UserAdapter(var list: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var namaText: TextView
        var emailText: TextView
        var passText: TextView
        var dateText: TextView

        init {
            namaText = view.findViewById(R.id.namaText)
            emailText = view.findViewById(R.id.emailText)
            passText = view.findViewById(R.id.passwordText)
            dateText = view.findViewById(R.id.dateText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaText.text = list[position].nama
        holder.emailText.text = list[position].email
        holder.passText.text = list[position].pass
        holder.dateText.text = list[position].tanggal
    }

    override fun getItemCount(): Int {
        return list.size
    }
}