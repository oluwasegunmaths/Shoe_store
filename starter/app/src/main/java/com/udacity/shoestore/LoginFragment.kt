package com.udacity.shoestore

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: ShoeListViewModel by lazy {
        ViewModelProvider(this.requireActivity()).get(ShoeListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToWelcomeFragment.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    tryToSignIn()
                }
            }
        })

        return binding.root
    }

    private fun tryToSignIn() {
        if (!binding.emailSigninEdittext.text.isNullOrEmpty() && !binding.emailSigninPasswordEdittext.text.isNullOrEmpty()) {
            val sharedPreferences: SharedPreferences = requireActivity().getPreferences(MODE_PRIVATE)
            sharedPreferences.edit().putBoolean("HAS SIGNED IN", true).apply()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            viewModel.doneNavigatingToWelcome()
        } else {
            Toast.makeText(requireContext(), "You have not filled in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}