package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding
import java.nio.charset.Charset
import java.util.Date

data class DataContent (
    val data: ArrayList<ListOfMeal>
)
data class ListOfMeal (
    val name_fr: String,
    val name_en: String,
    val items: ArrayList<MealDetail>
)
data class MealDetail (
    val id: Int,
    val name_fr: String,
    val name_en: String,
    val id_category: Int,
    val categ_name_fr: String,
    val categ_name_en: String,
    val images: ArrayList<String>,
    val ingredients: ArrayList<Ingredients>,
    val prices: ArrayList<Prices>
)
data class Ingredients (
    val id: Int,
    val id_shop: Int,
    val name_fr: String,
    val name_en: String,
    val create_date: Date,
    val update_date: Date,
    val id_pizza: Int
)
data class Prices (
    val id: Int,
    val id_pizza: Int,
    val id_size: Int,
    val price: Float,
    val create_date: Date,
    val update_date: Date,
    val size: String
)


class MealListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealListBinding
    private var itemsList = ArrayList<String>()
    private lateinit var myCategoryAdapter : CategorieAdapter
    private lateinit var parsedData : DataContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Getting item list
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, "http://test.api.catering.bluecodegames.com/menu",
                Response.Listener { response ->
                    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
                    parsedData = gson.fromJson<DataContent>(response, DataContent::class.java)
                    Log.d("##########HUMAN############", parsedData.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            ){
                override fun getBody(): ByteArray {
                    return "{\"id_shop\": 1 }".toByteArray(Charset.defaultCharset())
                }
            }
        Volley.newRequestQueue(this).add(stringReq)

        //Rendering menu
        val extras = intent.extras
        var menuName = extras?.getString("menu")
        var menuList = intent.getStringArrayListExtra("meal_list")
        if (menuName != null && menuList != null) {
            supportActionBar?.title = menuName

            myCategoryAdapter = CategorieAdapter(menuList) {
                    mealName -> myOnClickItem(mealName)
            }
            val layoutManager = LinearLayoutManager(applicationContext)
            binding.recycleView.layoutManager = layoutManager
            binding.recycleView.adapter = myCategoryAdapter
        }

        //Adding back button
        binding.buttonBack.setOnClickListener {
            val myIntent = Intent(this@MealListActivity, HomeActivity::class.java)
            startActivity(myIntent)
        }


    }
    private fun myOnClickItem(mealName: String){
        //Log.d("##########HUMAN############", message)
        val myIntent = Intent(this@MealListActivity, MealDetailActivity::class.java)
        myIntent.putExtra("meal", mealName)
        startActivity(myIntent)
    }
}

