package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityHomeBinding
import java.io.File

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myIntent = Intent(this@HomeActivity, MealListActivity::class.java)

        binding.buttonStarter.setOnClickListener {
            myIntent.putExtra("meal_list_number", 0)
            startActivity(myIntent)
            //myIntent.putExtra("menu", "starters")
            //Toast.makeText(this@HomeActivity, "Tu as cliqué sur Entrées !", Toast.LENGTH_SHORT).show()
            //val myMealList = resources.getStringArray(R.array.starters).toList() as ArrayList<String>
        }
        binding.buttonMainCourse.setOnClickListener {
            myIntent.putExtra("meal_list_number", 1)
            startActivity(myIntent)
        }
        binding.buttonDessert.setOnClickListener {
            myIntent.putExtra("meal_list_number", 2)
            startActivity(myIntent)
        }
        //Reseting cart in preferences and in local file
        val localCartPreferences = this.getSharedPreferences("localCartPreferences", MODE_PRIVATE)
        val preferencesEditor = localCartPreferences.edit()
        preferencesEditor.putInt("nbItemsInCart", 0)
        preferencesEditor.apply()
        val file = File(this.filesDir, "localCart.txt")
        file.delete()
    }
    override fun onDestroy() {
        Log.d("##########HUMAN############", "Home has been destroyed !!!!!")
        super.onDestroy()
    }
}