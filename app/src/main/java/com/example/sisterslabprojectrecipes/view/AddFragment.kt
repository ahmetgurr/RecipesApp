package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentAddBinding
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
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)

        tasarim.addFragment = this
        tasarim.addToolbarName = "Tarif Ekle"

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AddViewModel by viewModels ()
        viewModel = tempViewModel
    }

    fun buttonSave(recipe_name:String, recipe_content:String){
        viewModel.save(recipe_name, recipe_content)
    }
}