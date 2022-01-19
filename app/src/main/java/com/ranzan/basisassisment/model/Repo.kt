package com.ranzan.basisassisment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranzan.basisassisment.model.remote.Network
import com.ranzan.basisassisment.model.remote.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject

class Repo {

    private var list = mutableListOf<ResponseModel>()
    private val liveData = MutableLiveData<List<ResponseModel>>()


    fun fetchData(): Disposable =
        Network.getApiClient().getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                val str: String = t.substring(1)
                val jsonData = JSONObject(str)
                try {
                    val responseArray: JSONArray = jsonData.getJSONArray("data")
                    for (i in 0 until responseArray.length()) {
                        val jsonObject: JSONObject = responseArray.getJSONObject(i)

                        val id = jsonObject.getString("id")
                        val text = jsonObject.getString("text")
                        list.add(ResponseModel(id.toInt(), text))
                    }
                } finally {
                    liveData.postValue(list)
                }
            }

    fun getLiveData() = liveData as LiveData<List<ResponseModel>>

}