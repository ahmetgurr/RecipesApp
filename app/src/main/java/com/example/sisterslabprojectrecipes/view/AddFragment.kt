package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentAddBinding
import com.example.sisterslabprojectrecipes.util.gecisYap
import com.example.sisterslabprojectrecipes.viewmodel.AddViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddFragment : Fragment() {
    private lateinit var tasarim: FragmentAddBinding
    private lateinit var viewModel: AddViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)

        //geri dönmüyor / toast mesajı göstermiyor!!!!!!!!!!!!!!!!!!!!
        tasarim = FragmentAddBinding.inflate(inflater,container,false)
        /*
        tasarim.buttonUpdate.setOnClickListener{
            val gecis = AddFragmentDirections.actionAddFragmentToRecipeListFragment()
            Navigation.findNavController(it).navigate(gecis)
            Toast.makeText(context,"Tarif Eklendi", Toast.LENGTH_SHORT).show()
        }
         */
        tasarim.buttonUpdate.setOnClickListener{
            viewModel.save(tasarim.editTextAddName.text.toString(), tasarim.editTextAddContent.text.toString())
            val gecis = AddFragmentDirections.actionAddFragmentToRecipeListFragment()
            Navigation.findNavController(it).navigate(gecis)
            Toast.makeText(context,"Tarif Eklendi", Toast.LENGTH_SHORT).show()
        }


        tasarim.viewModel = viewModel
        tasarim.addToolbarName = "Tarif Ekle"
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AddViewModel by viewModels ()
        viewModel = tempViewModel
    }

    fun buttonSave(recipeName:String, recipeContent:String){
        viewModel.save(recipeName, recipeContent)
    }
}