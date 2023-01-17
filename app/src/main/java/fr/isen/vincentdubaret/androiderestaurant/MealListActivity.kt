package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset


class MealListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealListBinding
    private var itemsList = ArrayList<String>()
    private lateinit var myCategoryAdapter : CategorieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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

        binding.buttonBack.setOnClickListener {
            val myIntent = Intent(this@MealListActivity, HomeActivity::class.java)
            startActivity(myIntent)
        }

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val requestBody = "{\"id_shop\": 1 }"
        val stringReq : StringRequest =
            object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    Log.d("API", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            ){
                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
        queue.add(stringReq)
    }
    private fun myOnClickItem(mealName: String){
        //Log.d("##########HUMAN############", message)
        val myIntent = Intent(this@MealListActivity, MealDetailActivity::class.java)
        myIntent.putExtra("meal", mealName)
        startActivity(myIntent)
    }
}

