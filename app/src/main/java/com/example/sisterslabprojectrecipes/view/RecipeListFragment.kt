package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.adapter.RecipeRecyclerAdapter
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeListBinding
import com.example.sisterslabprojectrecipes.util.gecisYap
import com.example.sisterslabprojectrecipes.viewmodel.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipeListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentRecipeListBinding
    private lateinit var viewModel: RecipeListViewModel
    private lateinit var adapter: RecipeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)//toolbarı bağladık

        val tempViewModel: RecipeListViewModel by viewModels()
        viewModel = tempViewModel

        binding.recipeListFragment = this

        viewModel.getRecipes()

        viewModel.recipesList.observe(viewLifecycleOwner) {
            adapter = RecipeRecyclerAdapter(it, viewModel)
            binding.racipeListRV.adapter = adapter
            adapter.notifyDataSetChanged()//güncelleme işlemi
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

        return binding.root
    }

    //ekleme sayfasına geçiş
    fun fabClick(it: View) {
        Navigation.gecisYap(R.id.action_recipeListFragment_to_addFragment, it)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (query != null) {
                viewModel.searchRecipe(query)
                viewModel.recipeSearch.observe(viewLifecycleOwner) {
                    adapter = RecipeRecyclerAdapter(it.recipes, viewModel)
                    binding.racipeListRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            if (newText != null) {
                viewModel.searchRecipe(newText)
                viewModel.recipeSearch.observe(viewLifecycleOwner) {
                    adapter = RecipeRecyclerAdapter(it.recipes, viewModel)
                    binding.racipeListRV.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }
}