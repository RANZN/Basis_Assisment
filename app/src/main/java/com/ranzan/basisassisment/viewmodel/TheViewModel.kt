package com.ranzan.basisassisment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranzan.basisassisment.model.Repo
import com.ranzan.basisassisment.model.remote.ResponseModel

class TheViewModel(private val repo: Repo) : ViewModel() {

    fun fetchData() = repo.fetchData1()
    fun getData(): LiveData<List<ResponseModel>> = repo.getLiveData()

}

class ViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TheViewModel(repo) as T
    }
}