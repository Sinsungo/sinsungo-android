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

    init {
        _kakaoId.value = ""
        _kakaoNickName.value = ""
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
}