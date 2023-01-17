package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding
import java.io.Serializable
import java.nio.charset.Charset
import java.util.Date

class MealListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealListBinding
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
                    parsedData = gson.fromJson(response, DataContent::class.java)
                    renderMenu()
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

        //Adding back button
        binding.buttonBack.setOnClickListener {
            val myIntent = Intent(this@MealListActivity, HomeActivity::class.java)
            startActivity(myIntent)
        }
    }
    private fun renderMenu() {
        var mealListNumber = intent.extras?.getInt("meal_list_number")
        if (mealListNumber != null) {
            supportActionBar?.title = parsedData.data[mealListNumber].name_fr
            myCategoryAdapter = CategorieAdapter(this, parsedData.data[mealListNumber]) {
                    mealName -> myOnClickItem(mealName)
            }
            val layoutManager = LinearLayoutManager(applicationContext)
            binding.recycleView.layoutManager = layoutManager
            binding.recycleView.adapter = myCategoryAdapter
        }
    }

    //Onclick function to pass to recycle items
    private fun myOnClickItem(myMealDetail: MealDetail){
        //Log.d("##########HUMAN############", message)
        val myIntent = Intent(this@MealListActivity, MealDetailActivity::class.java)
        myIntent.putExtra("meal_infos", myMealDetail as Serializable)
        startActivity(myIntent)
    }
}

