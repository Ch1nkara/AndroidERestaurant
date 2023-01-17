package fr.isen.vincentdubaret.androiderestaurant

data class ListOfMeal (
    val name_fr: String,
    val name_en: String,
    val items: ArrayList<MealDetail>
)