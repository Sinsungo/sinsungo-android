package com.sinsungo.android.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _groupName = MutableLiveData<String>()
    val groupName: LiveData<String>
        get() = _groupName

//    private var _topText = MutableLiveData<String>()
//    val topText: LiveData<String>
//        get() = _topText

    init {
        _groupName.value = "임시그룹명"
    }

}