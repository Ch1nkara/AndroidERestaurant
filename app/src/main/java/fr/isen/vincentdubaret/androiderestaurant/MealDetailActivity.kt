package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
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
        val nbItemInCartTextView = findViewById<TextView>(R.id.nb_in_cart)
        if (nbItemInCart != 0) {
            nbItemInCartTextView.text = nbItemInCart.toString()
            nbItemInCartTextView.visibility = View.VISIBLE
        }

        binding.mealName.text = myMealDetail.name_fr
        binding.mealCount.text = "1"
        binding.total.text = this.getString(R.string.detail_total, myMealDetail.prices[0].price.toString())
        var ingredientsList = ""
        for (ingredient: Ingredients in myMealDetail.ingredients) {
            ingredientsList += ingredient.name_fr + ", "
        }
        binding.ingredients.text = ingredientsList.dropLast(2)
        binding.viewPager.adapter = PictureAdapter(this, myMealDetail.images)
        binding.mealAdd.setOnClickListener {
            val nbMeal = binding.mealCount.text.toString().toInt() + 1
            binding.mealCount.text = nbMeal.toString()
            binding.total.text = this.getString(R.string.detail_total, (nbMeal * myMealDetail.prices[0].price).toString())
        }
        binding.mealRemove.setOnClickListener {
            val nbMeal = binding.mealCount.text.toString().toInt() - 1
            if (nbMeal > 0) {
                binding.mealCount.text = nbMeal.toString()
                binding.total.text = this.getString(R.string.detail_total, (nbMeal * myMealDetail.prices[0].price).toString())
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
            if (readContent != "{}" && readContent != "") {
                myLocalCart = Gson().fromJson(readContent, LocalCart::class.java)
            }
            myLocalCart.add(newOrder)
            try {
                file.writeText(Gson().toJson(myLocalCart))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Log.d("##########HUMAN############", myLocalCart.toString())
            //Saving cart item numbers in a shared preference
            val preferencesEditor = localCartPreferences.edit()
            nbItemInCart += binding.mealCount.text.toString().toInt()
            preferencesEditor.putInt("nbItemsInCart", nbItemInCart)
            preferencesEditor.apply()
            nbItemInCartTextView.text = nbItemInCart.toString()
            nbItemInCartTextView.visibility = View.VISIBLE
            //Printing success
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Panier à jour")
            alertBuilder.setMessage("La panier a été mis à jour")
            alertBuilder.setPositiveButton("Ok") { _, _ -> }
            alertBuilder.show()
        }
        findViewById<ImageView>(R.id.cart_image).setOnClickListener {
            val myIntent = Intent(this@MealDetailActivity, CheckOutActivity::class.java)
            startActivity(myIntent)
        }
    }


}