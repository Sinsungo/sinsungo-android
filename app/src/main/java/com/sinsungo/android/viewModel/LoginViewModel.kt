package com.sinsungo.android.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private var _kakaoId = MutableLiveData<String>()
    val kakaoId: LiveData<String>
        get() = _kakaoId

    private var _kakaoNickName = MutableLiveData<String>()
    val kakaoNickName: LiveData<String>
        get() = _kakaoNickName

    private var _googleEmail = MutableLiveData<String>()
    val googleEmail: LiveData<String>
        get() = _googleEmail

    private var _googleToken = MutableLiveData<String>()
    val googleToken: LiveData<String>
        get() = _googleToken

    private var _googleTokenAuth = MutableLiveData<String>()
    val googleTokenAuth: LiveData<String>
        get() = _googleTokenAuth

    private var _googleNickName = MutableLiveData<String>()
    val googleNickName: LiveData<String>
        get() = _googleNickName

    private var _googleId = MutableLiveData<String>()
    val googleId: LiveData<String>
        get() = _googleId

    init {
        _kakaoId.value = ""
        _kakaoNickName.value = ""
        _googleEmail.value = ""
        _googleToken.value = ""
        _googleNickName.value = ""
        _googleTokenAuth.value = ""
        _googleId.value = ""
    }

    fun setKakaoId(email: String) {
        _kakaoId.value = email
    }

    fun getKakaoId() : String {
        return _kakaoId.value.toString()
    }

    fun setKakaoNickName(name: String) {
        _kakaoNickName.value = name
    }

    fun getKakaoNickName() : String {
        return _kakaoNickName.value.toString()
    }

    fun setGoogleEmail(email: String) {
        _googleEmail.value = email
    }

    fun getGoogleEmail() : String {
        return _googleEmail.value.toString()
    }

    fun setGoogleToken(token: String) {
        _googleToken.value = token
    }

    fun getGoogleToken() : String {
        return _googleToken.value.toString()
    }

    fun setGoogleTokenAuth(tokenAuth: String) {
        _googleTokenAuth.value = tokenAuth
    }

    fun getGoogleTokenAuth() : String {
        return _googleTokenAuth.value.toString()
    }

    fun setGoogleNickName(name: String) {
        _googleNickName.value = name
    }

    fun getGoogleNickName() : String {
        return _googleNickName.value.toString()
    }

    fun setGoogleId(id: String) {
        _googleId.value = id
    }

    fun getGoogleId() : String {
        return _googleId.value.toString()
    }
}