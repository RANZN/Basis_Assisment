package com.ranzan.basisassisment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.ranzan.basisassisment.R
import com.ranzan.basisassisment.databinding.ActivityMainBinding
import com.ranzan.basisassisment.model.Repo
import com.ranzan.basisassisment.model.remote.ResponseModel
import com.ranzan.basisassisment.view.adapter.CardAdapter
import com.ranzan.basisassisment.viewmodel.TheViewModel
import com.ranzan.basisassisment.viewmodel.ViewModelFactory
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
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

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setProgressBar(position + 1)
            }
        })
    }


    private fun setProgressBar(pos: Int) {
        binding.progressBar.apply {
            progress = pos
            max = dataList.size
        }
    }

    private fun setUI(list: List<ResponseModel>) {
        val cardAdapter = CardAdapter(list)
        binding.viewPager.apply {
            adapter = cardAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}

