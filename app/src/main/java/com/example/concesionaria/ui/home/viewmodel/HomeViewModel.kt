package com.example.concesionaria.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.concesionaria.utils.Utils
import com.example.concesionaria.model.dto.HousesEnvironmentResponse
import com.example.concesionaria.model.dto.HousesResponse
import com.example.concesionaria.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(private val serviceImp: Repository = Repository()) : ViewModel() {

    val nickNameData = MutableLiveData<Boolean>()

    val data = MutableLiveData<HousesResponse>()

    fun checkNickName(nickName: String) {
        nickNameData.postValue(Utils.checkNickName(nickName))
    }

    fun getHouses() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = serviceImp.getHouses()
            if (call.isSuccessful) {
                data.postValue(call.body())
            }
        }
    }
}
