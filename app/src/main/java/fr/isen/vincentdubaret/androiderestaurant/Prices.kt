package fr.isen.vincentdubaret.androiderestaurant

import java.io.Serializable
import java.util.Date

data class Prices (
    val id: Int,
    val id_pizza: Int,
    val id_size: Int,
    val price: Float,
    val create_date: Date,
    val update_date: Date,
    val size: String
) : Serializable