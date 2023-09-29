package com.example.sisterslabprojectrecipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.ItemRecipeBinding
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.util.gecisYap
import com.example.sisterslabprojectrecipes.view.RecipeListFragmentDirections
import com.example.sisterslabprojectrecipes.viewmodel.RecipeListViewModel

class RecipeRecyclerAdapter(
    var recipeList: List<Recipe>,
    var viewModel: RecipeListViewModel
) : RecyclerView.Adapter<RecipeRecyclerAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(tasarim: ItemRecipeBinding) :
        RecyclerView.ViewHolder(tasarim.root) {

        var tasarim: ItemRecipeBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val tasarim: ItemRecipeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recipe, parent, false)
        return RecipeViewHolder(tasarim)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList.get(position)
        val t = holder.tasarim

        t.selectedRecipe = recipe

        t.satirCard.setOnClickListener {
            var id = recipe.recipe_id
            val gecis =
                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipeId = id)
            Navigation.gecisYap(it, gecis)
        }
    }

}



