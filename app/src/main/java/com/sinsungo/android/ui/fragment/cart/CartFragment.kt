package com.seunggyu.stitch.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sinsungo.android.R
import com.sinsungo.android.databinding.FragMainCartBinding
import com.sinsungo.android.viewModel.MainViewModel

class CartFragment : Fragment() {
    private lateinit var binding: FragMainCartBinding

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_main_cart, container, false)


        return binding.root
    }

}