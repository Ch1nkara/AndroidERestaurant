package fr.isen.vincentdubaret.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealDetailBinding
import java.io.*


class MealDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailBinding
    private var myLocalCart: LocalCart = LocalCart(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myMealDetail = intent.extras?.get("meal_infos") as MealDetail

        val localCartPreferences = this.getSharedPreferences("localCartPreferences", MODE_PRIVATE)
        var nbItemInCart = localCartPreferences.getInt("nbItemsInCart", 0)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar)
        findViewById<TextView>(R.id.action_bar_title).text = myMealDetail.name_fr
        val nbItemInCart_tv = findViewById<TextView>(R.id.nb_in_cart)
        if (nbItemInCart != 0) {
            nbItemInCart_tv.text = nbItemInCart.toString()
            nbItemInCart_tv.visibility = View.VISIBLE
        }

        binding.mealName.text = myMealDetail.name_fr
        binding.mealCount.text = "1"
        binding.total.text = "SOUS TOTAL " + myMealDetail.prices[0].price.toString() + "€"
        var ingredientsList = ""
        for (ingredient: Ingredients in myMealDetail.ingredients) {
            ingredientsList += ingredient.name_fr + ", "
        }
        binding.ingredients.text = ingredientsList.dropLast(2)
        binding.viewPager.adapter = PictureAdapter(this, myMealDetail.images)
        binding.mealAdd.setOnClickListener {
            var nbMeal = binding.mealCount.text.toString().toInt() + 1
            binding.mealCount.text = nbMeal.toString()
            binding.total.text = "SOUS TOTAL " + (nbMeal * myMealDetail.prices[0].price).toString() + "€"
        }
        binding.mealRemove.setOnClickListener {
            var nbMeal = binding.mealCount.text.toString().toInt() - 1
            if (nbMeal > 0) {
                binding.mealCount.text = nbMeal.toString()
                binding.total.text = "SOUS TOTAL " + (nbMeal * myMealDetail.prices[0].price).toString() + "€"
            }
        }
        binding.total.setOnClickListener {
            //Saving cart json in a file
            val newOrder = AnOrder(
                myMealDetail.id,
                binding.mealCount.text.toString().toInt(),
                myMealDetail.name_fr,
                myMealDetail.prices[0].price
            )
            val file = File(this.filesDir, "localCart.txt")
            var readContent = ""
            try {
                readContent = file.readText()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (readContent != "{}") {
                myLocalCart = Gson().fromJson(readContent, LocalCart::class.java)
            }
            myLocalCart.add(newOrder)
            try {
                file.writeText(Gson().toJson(myLocalCart))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //Saving cart item numbers in a shared preference
            val preferencesEditor = localCartPreferences.edit()
            nbItemInCart += binding.mealCount.text.toString().toInt()
            preferencesEditor.putInt("nbItemsInCart", nbItemInCart)
            preferencesEditor.apply()
            nbItemInCart_tv.text = nbItemInCart.toString()
            nbItemInCart_tv.visibility = View.VISIBLE
            //Printing success
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Panier à jour")
            alertBuilder.setMessage("La panier a été mis à jour")
            alertBuilder.setPositiveButton("Ok") { dialog, which -> }
            alertBuilder.show()
        }
    }


}