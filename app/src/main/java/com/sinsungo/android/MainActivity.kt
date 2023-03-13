package com.sinsungo.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.seunggyu.stitch.ui.fragment.home.HomeFragment
import com.seunggyu.stitch.ui.fragment.home.CartFragment
import com.seunggyu.stitch.ui.fragment.home.InfoFragment
import com.sinsungo.android.databinding.ActivityMainBinding
import com.sinsungo.android.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
    }

    fun init() {
        with(binding) {
            navigationView.run {
                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.action_home -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.frameLayout.id, HomeFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_search -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.frameLayout.id, CartFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_cart -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.frameLayout.id, CartFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_info -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(binding.frameLayout.id, InfoFragment())
                                .commitAllowingStateLoss()
                        }
                    }
                    true
                }
                selectedItemId = R.id.action_home
            }
        }
    }
}