package com.abhishek.myrecipeapp

//retrofit is used for making network requests and handling api communication in android applications.
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//build connection to baseUrl and do it in such a way that we get objects in kotlin
private val retrofit = Retrofit.Builder()//start process of building retrofit instance.
    .baseUrl("https://www.themealdb.com/api/json/v1/1/") //setting the baseUrl for the api
    .addConverterFactory(GsonConverterFactory.create()) //convert json response from the api to kotlin objects.
    .build() //finalizes the configuration and builds the retrofit instance.


val recipeService = retrofit.create(ApiService::class.java) //we want a service to get the categories.php file.

interface ApiService{
    @GET("categories.php")
    suspend fun getCategories():CategoriesResponse
}