package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myIntent = Intent(this@HomeActivity, MealListActivity::class.java)

        binding.buttonStarter.setOnClickListener {
            //Toast.makeText(this@HomeActivity, "Tu as cliqué sur Entrées !", Toast.LENGTH_SHORT).show()
            myIntent.putExtra("menu", "starters")
            startActivity(myIntent)
        }
        binding.buttonMainCourse.setOnClickListener {
            myIntent.putExtra("menu", "main courses")
            startActivity(myIntent)
        }
        binding.buttonDessert.setOnClickListener {
            myIntent.putExtra("menu", "desserts")
            startActivity(myIntent)
        }
    }
    override fun onDestroy() {
        Log.d("HUMAN", "Home has been destroyed !!!!!")
        super.onDestroy()
    }
}