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
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_right)
        val anim3 = AnimationUtils.loadAnimation(this, R.anim.anim_translate_right)
        lifecycleScope.launch {
            with(binding) {
                delay(100)
                btnKakaoLogin.startAnimation(anim)
                btnKakaoLogin.isVisible = true

                delay(100)
                binding.btnGoogleLogin.startAnimation(anim2)
                btnGoogleLogin.isVisible = true

                delay(100)
                binding.btnAppleLogin.startAnimation(anim3)
                btnAppleLogin.isVisible = true
            }
        }
    }
}