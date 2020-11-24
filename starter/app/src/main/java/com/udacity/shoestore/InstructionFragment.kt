package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding


class InstructionFragment : Fragment() {
    private val viewModel: ShoeListViewModel by lazy {
        ViewModelProvider(this.requireActivity()).get(ShoeListViewModel::class.java)
    }
    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_instruction, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigateToShoeListFragment.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment())
                    viewModel.doneNavigatingToShoeList()
                }
            }
        })
        return binding.root
    }

}