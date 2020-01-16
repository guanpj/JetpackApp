package com.me.guanpj.jetpackapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.me.guanpj.jetpackapp.data.bean.Component

class HomeViewModel internal constructor(homeRepository: HomeRepository) : ViewModel() {

    val listData: LiveData<List<Component>> = homeRepository.getPlants()

}