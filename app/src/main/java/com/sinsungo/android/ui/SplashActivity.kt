package com.sinsungo.android.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sinsungo.android.MainActivity
import com.sinsungo.android.databinding.ActivitySplashBinding
import com.sinsungo.android.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        setContentView(binding.root)

        var intent = Intent(this@SplashActivity, MainActivity::class.java)
        // 2초 지연
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }
}