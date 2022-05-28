package com.example.fruitsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fruitsapp.R
import com.example.fruitsapp.databinding.FragmentFruitListBinding

class FruitListFragment : Fragment() {

    private val viewModel: FruitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFruitListBinding.inflate(inflater)
        viewModel.getFruitList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = FruitListAdapter(FruitListener { fruit ->
            viewModel.onFruitClicked(fruit)
            findNavController()
                .navigate(R.id.action_fruitListFragment_to_fruitDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}