package fr.isen.vincentdubaret.androiderestaurant

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //set available choices for smoke, light and sound
        val button_starter = findViewById<Button>(R.id.button_starter)
        button_starter.setOnClickListener {
            //Toast.makeText(this@HomeActivity, "Tu as cliqué sur Entrées !", Toast.LENGTH_SHORT).show()
            val myIntent = Intent(this@HomeActivity, MealListActivity::class.java)
            startActivity(myIntent)
        }
        //set available choices for smoke, light and sound
        val button_main_course = findViewById<Button>(R.id.button_main_course)
        button_main_course.setOnClickListener {
            val myIntent = Intent(this@HomeActivity, MealListActivity::class.java)
            startActivity(myIntent)
        }
        //set available choices for smoke, light and sound
        val button_dessert = findViewById<Button>(R.id.button_dessert)
        button_dessert.setOnClickListener {
            val myIntent = Intent(this@HomeActivity, MealListActivity::class.java)
            startActivity(myIntent)
        }
    }
}