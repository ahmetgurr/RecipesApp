package com.example.sisterslabprojectrecipes.di

import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import com.example.sisterslabprojectrecipes.retrofit.ApiUtils
import com.example.sisterslabprojectrecipes.retrofit.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
// @Provides ->  provides an instance of the type that this function returns
//               bağımlılığın nasıl oluşturulacağını belirtmek için
// @Singleton -> tells Dagger that there should only be a single instance of this class in the app
//               bir bağımlılığın tek bir örneğinin olduğunu belirtmek için
// @InstallIn -> tells Dagger which Android class(es) should be used to create instances of the dependencies
//               bağımlılıkların nasıl oluşturulacağını belirtmek için
// @Module ->    tells Dagger that this class is a Dagger Module
//               Dagger'a bu sınıfın bir Dagger Modülü olduğunu söyler
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(rdao: RecipeDao) : RecipeRepository{
        return RecipeRepository(rdao)
    }

    @Provides
    @Singleton
    fun provideRecipeDao() : RecipeDao{
        return ApiUtils.getRecipeDao()
    }
}