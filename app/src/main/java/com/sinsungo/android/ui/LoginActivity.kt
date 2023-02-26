package com.sinsungo.android.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sinsungo.android.MainActivity
import com.sinsungo.android.R
import com.sinsungo.android.databinding.ActivityLoginBinding
import com.sinsungo.android.databinding.ActivitySplashBinding
import com.sinsungo.android.viewModel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(binding.root)

        // 2초 지연
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@LoginActivity, LoginActivity::class.java))
            finish()
        }
    }
}