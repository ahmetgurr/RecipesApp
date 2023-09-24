package com.example.sisterslabprojectrecipes.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.RecipeRecyclerRowBinding
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.util.gecisYap
import com.example.sisterslabprojectrecipes.view.RecipeListFragmentDirections
import com.example.sisterslabprojectrecipes.viewmodel.RecipeListViewModel
import com.google.android.material.snackbar.Snackbar
import java.math.MathContext


class RecipeRecyclerAdapter(val recipeList: ArrayList<Recipe>,
                            var mContext: Context,
                            var viewModel:RecipeListViewModel )
                            : RecyclerView.Adapter<RecipeRecyclerAdapter.RecipeViewHolder>(){

    inner class RecipeViewHolder(tasarim: RecipeRecyclerRowBinding) : RecyclerView.ViewHolder(tasarim.root){

        var tasarim: RecipeRecyclerRowBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
       /* val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recipe_recycler_row,parent,false)
        return RecipeViewHolder(RecipeRecyclerRowBinding.bind(view))
        */
        /*
        val binding = RecipeRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
         */

        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: RecipeRecyclerRowBinding = DataBindingUtil.inflate(layoutInflater,R.layout.recipe_recycler_row,parent,false)
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
            val gecis = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe = recipe)//(recipe = recipe)= nav_graph.xml deki arguments kısmında recipe parametresini eklememiz lazım.
            Navigation.gecisYap(it,gecis)
        }
        t.imageViewDelete.setOnClickListener {
            Snackbar.make(it,"${recipe.recipe_name} silinsin mi?",Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    viewModel.delete(recipe.recipe_id)
                }.show()
        }
    }



    fun recipeListUpdate(newRecipeList: List<Recipe>){
        recipeList.clear()
        recipeList.addAll(newRecipeList)
        notifyDataSetChanged()//adapteri güncellemek için
        Log.e("update", recipeList.toString())
    }

}