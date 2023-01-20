package fr.isen.vincentdubaret.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealDetailBinding

class MealDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myMealDetail = intent.extras?.get("meal_infos") as MealDetail
        supportActionBar?.title = myMealDetail.name_fr
        binding.mealName.text = myMealDetail.name_fr
        binding.mealCount.text = "0"
        binding.total.text = "TOTAL 0€"
        var ingredientsList = ""
        for (ingredient: Ingredients in myMealDetail.ingredients) {
            ingredientsList += ingredient.name_fr + ", "
        }
        binding.ingredients.text = ingredientsList.dropLast(2)

        binding.viewPager.adapter = PictureAdapter(this, myMealDetail.images)
    }
}