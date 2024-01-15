package com.abhishek.myrecipeapp
//viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    init {
        fetchCategories()
    }

    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            }
            catch (e: Exception){
                _categoryState.value = _categoryState.value
                    .copy(loading = false,
                    error ="error featching categories ${e.message}"
                )

            }
        }
    }


    //Data class to take care the state of recipes
    //need to know if we have the recipes or not.
    //and to know at which state of recipe we are;
    data class RecipeState(
        val loading: Boolean =true,//are we loading or not loading
        val list: List<Category> = emptyList(),//have a list or do not have a list
        val error: String? = null//error or no error
    )

}