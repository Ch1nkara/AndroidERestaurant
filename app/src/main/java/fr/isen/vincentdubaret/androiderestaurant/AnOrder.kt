package fr.isen.vincentdubaret.androiderestaurant

data class AnOrder (
    val mealId : Int,
    val mealQuantity : Int,
    val mealName : String,
    val mealPrice : Float
)