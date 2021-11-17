package com.rully.retrofitmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rully.retrofitmvvm.model.DataItem
import com.rully.retrofitmvvm.model.RetrofitConfig
import com.rully.retrofitmvvm.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<MutableList<DataItem>>()

    fun getUser() {
        RetrofitConfig.getApiService().getUsers().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body()?.data as MutableList<DataItem>?)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("Failure", t.message.toString())
            }
        })
    }

    fun setUser(): LiveData<MutableList<DataItem>> {
        return listUser
    }

}