package fr.isen.vincentdubaret.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MealDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        var mealName = intent.extras?.getString("meal")

        supportActionBar?.title = mealName
    }
}