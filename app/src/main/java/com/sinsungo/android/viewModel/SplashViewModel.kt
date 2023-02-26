package com.sinsungo.android.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    init {
        Log.e("MyTag","ViewModel로드")
    }

}