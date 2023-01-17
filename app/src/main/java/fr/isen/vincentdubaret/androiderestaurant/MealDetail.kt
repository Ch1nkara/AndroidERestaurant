package fr.isen.vincentdubaret.androiderestaurant

import java.io.Serializable

data class MealDetail (
    val id: Int,
    val name_fr: String,
    val name_en: String,
    val id_category: Int,
    val categ_name_fr: String,
    val categ_name_en: String,
    val images: ArrayList<String>,
    val ingredients: ArrayList<Ingredients>,
    val prices: ArrayList<Prices>
) : Serializable