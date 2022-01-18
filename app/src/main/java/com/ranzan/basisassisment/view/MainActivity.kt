package com.ranzan.basisassisment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ranzan.basisassisment.R
import com.ranzan.basisassisment.databinding.ActivityMainBinding
import com.ranzan.basisassisment.model.Repo
import com.ranzan.basisassisment.model.remote.ResponseModel
import com.ranzan.basisassisment.view.adapter.CardAdapter
import com.ranzan.basisassisment.view.adapter.ProgressData
import com.ranzan.basisassisment.viewmodel.TheViewModel
import com.ranzan.basisassisment.viewmodel.ViewModelFactory
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity(), ProgressData {
    private lateinit var viewModel: TheViewModel
    private lateinit var disposable: Disposable
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList: List<ResponseModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelFactory(Repo())).get(TheViewModel::class.java)

        disposable = viewModel.fetchData()

        viewModel.getData().observe(this, Observer {
            dataList = it
            setUI(it)
        })
        binding.reset.setOnClickListener {
            setUI(dataList)
        }
    }

    private fun setUI(list: List<ResponseModel>) {
        val cardAdapter = CardAdapter(list, this)
        binding.cardView.apply {
            adapter = cardAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun progressData(pos: Int, size: Int) {
        binding.progressBar.apply {
            progress = pos
            max = size
        }
    }
}

