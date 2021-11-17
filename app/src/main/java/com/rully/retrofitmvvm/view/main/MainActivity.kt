package com.rully.retrofitmvvm.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rully.retrofitmvvm.adapter.UserAdapter
import com.rully.retrofitmvvm.databinding.ActivityMainBinding
import com.rully.retrofitmvvm.model.DataItem
import com.rully.retrofitmvvm.model.RetrofitConfig
import com.rully.retrofitmvvm.model.UserResponse
import com.rully.retrofitmvvm.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.setUser().observe(this, {
            if (it != null) {
                adapter.setData(it)
            }
        })

        binding.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvUser.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(this@MainActivity, layoutManager.orientation)
            binding.rvUser.addItemDecoration(itemDecoration)
            binding.rvUser.setHasFixedSize(true)
            binding.rvUser.adapter = adapter
            mainViewModel.getUser()
        }



//        showUsers()
    }

    private fun showUsers() {
        val client = RetrofitConfig.getApiService().getUsers()
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        adapter = UserAdapter()
                        adapter.setData(responseBody.data as MutableList<DataItem>)
                        val layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.rvUser.layoutManager = layoutManager
                        val itemDecoration = DividerItemDecoration(this@MainActivity, layoutManager.orientation)
                        binding.rvUser.addItemDecoration(itemDecoration)
                        binding.rvUser.setHasFixedSize(true)
                        binding.rvUser.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }

        })
    }
}