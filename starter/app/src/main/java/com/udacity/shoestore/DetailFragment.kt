package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ShoeListViewModel by lazy {
        ViewModelProvider(this.requireActivity()).get(ShoeListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.navigateToShoeListFragment.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    findNavController().popBackStack()
                    viewModel.doneNavigatingToShoeList()
                }
            }
        })
        viewModel.showMissingFieldsToast.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    Toast.makeText(requireContext(), "You have to fill in all fields", Toast.LENGTH_SHORT).show()
                    viewModel.shownToast()
                }
            }
        })
        return binding.root
    }

}