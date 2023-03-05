package com.sinsungo.android.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sinsungo.android.MainActivity
import com.sinsungo.android.R
import com.sinsungo.android.databinding.ActivityLoginBinding
import com.sinsungo.android.databinding.ActivitySplashBinding
import com.sinsungo.android.viewModel.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
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

        // anim 하나만으로 적용하면 이상하게 끊기는 이유를 모르겠습니다,,
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_translate_right)
        lifecycleScope.launch {
            with(binding) {
                delay(100)
                btnKakaoLogin.isVisible = true
                btnKakaoLogin.startAnimation(anim)

                delay(200)
                btnGoogleLogin.isVisible = true
                btnGoogleLogin.startAnimation(anim)

                delay(200)
                btnAppleLogin.isVisible = true
                btnAppleLogin.startAnimation(anim)
            }
        }
    }
}