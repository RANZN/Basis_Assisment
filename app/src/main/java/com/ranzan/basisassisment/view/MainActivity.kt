package com.ranzan.basisassisment.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ranzan.basisassisment.R
import com.ranzan.basisassisment.model.Repo
import com.ranzan.basisassisment.model.remote.ResponseModel
import com.ranzan.basisassisment.viewmodel.TheViewModel
import com.ranzan.basisassisment.viewmodel.ViewModelFactory
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TheViewModel
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelFactory(Repo())).get(TheViewModel::class.java)

        disposable = viewModel.fetchData()

        viewModel.getData().observe(this, Observer {
            setUI(it!!)
        })
    }

    private fun setUI(list: List<ResponseModel>) {

    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}

