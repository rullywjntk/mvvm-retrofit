package com.rully.retrofitmvvm.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rully.retrofitmvvm.databinding.ListUserBinding
import com.rully.retrofitmvvm.model.DataItem

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users = mutableListOf<DataItem>()

    fun setData(user: MutableList<DataItem>) {
        this.users = user
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(list: DataItem) {
            binding.apply {
                tvName.text = "Name :  ${list.name}"
                tvEmail.text = "Email : ${list.email}"
                tvGender.text = "Gender : ${list.gender}"
                tvStatus.text = "Status : ${list.status}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}