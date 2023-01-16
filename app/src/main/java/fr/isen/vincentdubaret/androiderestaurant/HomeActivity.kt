package fr.isen.vincentdubaret.androiderestaurant

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //set available choices for smoke, light and sound
        val button_dessert = findViewById<Button>(R.id.button_dessert)
        Toast.makeText(applicationContext, "Hello toast!", Toast.LENGTH_SHORT).show()

    }
}