package com.sinsungo.android.ui

import android.content.ContentValues
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
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.sinsungo.android.MainActivity
import com.sinsungo.android.R
import com.sinsungo.android.databinding.ActivityLoginBinding
import com.sinsungo.android.databinding.ActivitySplashBinding
import com.sinsungo.android.viewModel.LoginViewModel
import com.sinsungo.android.viewModel.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

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

        with(binding) {
            // 카카오 로그인
            btnKakaoLogin.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    UserApiClient.instance.loginWithKakaoTalk(
                        this@LoginActivity,
                        callback = kakaoCallback
                    )
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(
                        this@LoginActivity,
                        callback = kakaoCallback
                    )
                }
            }
        }
    }

    private val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        Log.e("tag",Utility.getKeyHash(this))
        if (error != null) {
            Log.e("kakao result", "로그인 실패- $error")
        } else if (token != null) {
            Log.e("kakao result", "로그인성공 - 토큰 ${token.accessToken} id토큰 ${token.idToken} ")
            UserApiClient.instance.me { user, mError ->
                if (mError != null) {
                    Log.e(ContentValues.TAG, "사용자 정보 요청 실패 $error")
                } else if (user != null) {
                    Log.e(ContentValues.TAG, "사용자 정보 요청 성공 : $user")
                    viewModel.setKakaoNickName(user.kakaoAccount?.profile?.nickname.toString())
                    viewModel.setKakaoId(user.id.toString())

                    Log.e("kakaoId", viewModel.getKakaoId())
                    Log.e("kakaoNickName", viewModel.getKakaoNickName())
                }
            }
        }
    }

}