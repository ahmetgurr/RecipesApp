package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.adapter.RecipeRecyclerAdapter
import com.example.sisterslabprojectrecipes.databinding.FragmentAddBinding
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeListBinding
import com.example.sisterslabprojectrecipes.util.gecisYap
import com.example.sisterslabprojectrecipes.viewmodel.AddViewModel
import com.example.sisterslabprojectrecipes.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var tasarim: FragmentRecipeListBinding
    private lateinit var viewModel: RecipeListViewModel
   // private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //tasarim = FragmentRecipeListBinding.inflate(inflater)
        //tasarımı bağladık
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)
        tasarim.recipeListFragment = this

        tasarim.recipeListToolBarName = "Tarifler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar)//toolbarı bağladık


        viewModel.recipeList.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                val adapter = RecipeRecyclerAdapter(requireContext(), data, viewModel)
                tasarim.racipeListRV.adapter = adapter
            }
        }

            requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)//menüyü fragmenta bağladık

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@RecipeListFragment)//arama işlemi için
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RecipeListViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun fabClick(it: View) {
        Navigation.gecisYap(R.id.action_recipeListFragment_to_addFragment, it)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecipe()
    }


}