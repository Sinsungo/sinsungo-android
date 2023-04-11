package com.sinsungo.android.ui

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.sinsungo.android.R
import com.sinsungo.android.databinding.ActivityLoginBinding
import com.sinsungo.android.viewModel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var GoogleSignResultLauncher: ActivityResultLauncher<Intent>
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LOG","init")

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

        // 구글 로그인 로직
        val gso = Builder(DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
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

            // 구글 로그인
            btnGoogleLogin.setOnClickListener {
                val signIntent: Intent = mGoogleSignInClient.signInIntent
                GoogleSignResultLauncher.launch(signIntent)
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

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            viewModel.setGoogleEmail(account?.email.toString())
            viewModel.setGoogleToken(account?.idToken.toString())
            viewModel.setGoogleTokenAuth(account?.serverAuthCode.toString())
            viewModel.setGoogleNickName(account?.displayName.toString())
            viewModel.setGoogleId(account?.id.toString())

            Log.e("Google account Email",viewModel.getGoogleEmail())
            Log.e("Google account Token",viewModel.getGoogleToken())
            Log.e("Google account TokenAuth", viewModel.getGoogleTokenAuth())
            Log.e("Google account NickName", viewModel.getGoogleNickName())
            Log.e("Google account Id", viewModel.getGoogleId())
        } catch (e: ApiException){
            Log.e("Google account","signInResult:failed Code = " + e.statusCode)
        }
    }
}