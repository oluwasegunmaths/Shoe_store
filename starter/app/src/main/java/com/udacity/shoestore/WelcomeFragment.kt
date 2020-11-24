package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private val viewModel: ShoeListViewModel by lazy {
        ViewModelProvider(this.requireActivity()).get(ShoeListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_welcome, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigateToInstructionFragment.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
                    viewModel.doneNavigatingToInstructions()
                }
            }
        })
        return binding.root
    }
}