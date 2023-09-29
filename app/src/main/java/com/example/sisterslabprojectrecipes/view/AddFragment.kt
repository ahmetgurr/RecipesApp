package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.sisterslabprojectrecipes.databinding.FragmentAddBinding
import com.example.sisterslabprojectrecipes.viewmodel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AddViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)
        binding = FragmentAddBinding.inflate(inflater,container,false)
/*
        binding.buttonUpdate.setOnClickListener{
            viewModel.addRecipes(binding.editTextAddName.text.toString(), binding.editTextAddContent.text.toString())
            val gecis = AddFragmentDirections.actionAddFragmentToRecipeListFragment()
            Navigation.findNavController(it).navigate(gecis)
            Toast.makeText(context,"Tarif Eklendi", Toast.LENGTH_SHORT).show()
        }
 */
        val tempViewModel: AddViewModel by viewModels()
        viewModel = tempViewModel

        binding.viewModel = viewModel
        binding.addToolbarName = "Tarif Ekle"
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val tempViewModel : AddViewModel by viewModels ()
        //viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdate.setOnClickListener {
            val foodName = binding.editTextAddName.text.toString()
            val recipe = binding.editTextAddContent.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.addRecipes(foodName, recipe)
            }

            viewModel.recipeAdd.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    Toast.makeText(requireContext(), "Tarif Eklendi", Toast.LENGTH_SHORT).show()
                    val action = AddFragmentDirections.actionAddFragmentToRecipeListFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Tarif Eklenemedi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}