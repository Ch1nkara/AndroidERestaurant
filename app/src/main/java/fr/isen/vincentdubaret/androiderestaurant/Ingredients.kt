package fr.isen.vincentdubaret.androiderestaurant

import java.util.Date

data class Ingredients (
    val id: Int,
    val id_shop: Int,
    val name_fr: String,
    val name_en: String,
    val create_date: Date,
    val update_date: Date,
    val id_pizza: Int
)