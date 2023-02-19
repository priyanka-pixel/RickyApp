package com.example.rickyapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickyapp.characterdetails.CharacterDetailViewModel
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.databinding.FragmentSecondBinding
import com.example.rickyapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: CharacterDetailViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Handle incoming data
         * Safe calls ?
         * Elvis ?:
         * let
         * null
         */

        arguments?.getInt("ID").let {
           // Log.i("ID", ""+arguments?.getInt("ID"))
            // API CALL
            viewModel.startDetailsCall(it!!)
        }

        startObserversForDetails()
//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindDetailsData(result: Result){
        binding.textView.text = result.name
        binding.textView2.text = result.gender
        binding.textView3.text= result.species
        binding.textView4.text = result.status

        Glide.with(binding.root)
            .load(result.image)
            .transform(CircleCrop())
            .into(binding.imageView)

    }

    fun startObserversForDetails(){
        viewModel.character.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS ->{ //200
                 bindDetailsData(it.data!!)
                }
                Resource.Status.ERROR ->{
                    Log.i("Error", it.message.toString())

                }
                Resource.Status.LOADING -> {
                    // Progress Dialog
                }
            }
        }
    }

}