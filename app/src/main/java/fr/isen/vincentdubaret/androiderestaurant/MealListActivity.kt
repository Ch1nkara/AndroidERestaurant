package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding

class MealListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extras = intent.extras
        if (extras != null) {
            var value = extras.getString("menu")
            binding.menuName.text = value
            //The key argument here must match that used in the other activity
        }

        binding.buttonBack.setOnClickListener {
            val my_intent = Intent(this@MealListActivity, HomeActivity::class.java)
            startActivity(my_intent)
        }
    }
}