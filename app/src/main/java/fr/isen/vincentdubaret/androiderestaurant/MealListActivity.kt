package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MealListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_list)
        val button_back = findViewById<Button>(R.id.button_back)
        button_back.setOnClickListener {
            //Toast.makeText(this@HomeActivity, "Tu as cliqué sur Desserts !", Toast.LENGTH_SHORT).show()
            val myIntent = Intent(this@MealListActivity, HomeActivity::class.java)
            startActivity(myIntent)
        }
    }
}