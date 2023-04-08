package com.seunggyu.stitch.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sinsungo.android.R
import com.sinsungo.android.databinding.FragMainHomeBinding
import com.sinsungo.android.viewModel.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragMainHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragMainHomeBinding.inflate(inflater, container, false)

        initObserver()

        return binding.root
    }

    fun initObserver() {
        viewModel.groupName.observe(requireActivity()) {
            it?.let {
                binding.tvHomeToptext.text = getString(R.string.home_toptext, viewModel.groupName.value)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}