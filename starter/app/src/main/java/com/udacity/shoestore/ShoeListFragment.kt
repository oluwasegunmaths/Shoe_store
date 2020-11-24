package com.udacity.shoestore


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by lazy {
        ViewModelProvider(this.requireActivity()).get(ShoeListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.shoeList.observe(viewLifecycleOwner, {
            it?.let {
                binding.textViewEmptyList.visibility = View.GONE
                for (newShoe: Shoe in it) {
                    val textView = TextView(this.context)
                    val textToShow = "Shoe name is " + newShoe.name + "\n Shoe company is " + newShoe.company + "\n" +
                            "Shoe size is " + newShoe.size + "\n Shoe description is " + newShoe.description
                    textView.text = textToShow
                    val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(10, 10, 10, 10)
                    textView.layoutParams = params
                    textView.textSize = 20F
                    textView.gravity = Gravity.CENTER

                    binding.linearLayout.addView(textView)
                }

            }
        })
        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment())
                    viewModel.doneNavigatingToDetail()
                }
            }
        })

        return binding.root
    }

}