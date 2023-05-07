package com.sinsungo.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.seunggyu.stitch.ui.fragment.home.HomeFragment
import com.seunggyu.stitch.ui.fragment.home.CartFragment
import com.seunggyu.stitch.ui.fragment.home.InfoFragment
import com.sinsungo.android.databinding.ActivityMainBinding
import com.sinsungo.android.databinding.ActivitySplashBinding
import com.sinsungo.android.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                                .replace(R.id.frameLayout, HomeFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_search -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, CartFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_cart -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, CartFragment())
                                .commitAllowingStateLoss()
                        }
                        R.id.action_info -> {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.frameLayout, InfoFragment())
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