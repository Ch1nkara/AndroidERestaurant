package fr.isen.vincentdubaret.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding

class MealListActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMealListBinding

    private val itemsList = ArrayList<String>()
    private lateinit var myCategoryAdapter : CategorieAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMealListBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(R.layout.activity_meal_list)

        val extras = intent.extras
        if (extras != null) {
            var value = extras.getString("menu")
            supportActionBar?.title = value
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycle_view)
        myCategoryAdapter = CategorieAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = myCategoryAdapter
        prepareItems()


        //binding.buttonBack.setOnClickListener {
        //    val my_intent = Intent(this@MealListActivity, HomeActivity::class.java)
        //    startActivity(my_intent)
        //}
    }
    private fun prepareItems() {
        itemsList.add("Item 1")
        itemsList.add("Item 2")
        itemsList.add("Item 3")
        itemsList.add("Item 4")
        Log.d("HUMAN", itemsList.toString())
        myCategoryAdapter.notifyDataSetChanged()
    }

}