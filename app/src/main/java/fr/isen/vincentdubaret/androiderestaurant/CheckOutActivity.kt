package fr.isen.vincentdubaret.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityCheckOutBinding
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityMealListBinding
import java.io.File
import java.io.IOException
import java.io.Serializable

class CheckOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckOutBinding
    private lateinit var myCheckOutAdapter : CheckOutAdapter
    private var myLocalCart: LocalCart = LocalCart(ArrayList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "CHECK OUT"

        binding.checkout.setOnClickListener {
            Toast.makeText(this@CheckOutActivity, "Vous avez validé votre panier !", Toast.LENGTH_LONG).show()
        }

        var arrayTemp = ArrayList<AnOrder>()
        arrayTemp.add(AnOrder(1,2,"truc", 10f))
        arrayTemp.add(AnOrder(2,3,"bidule", 11f))
        arrayTemp.add(AnOrder(5,1,"muche", 13f))
        Log.d("##########HUMAN############", arrayTemp.toString())

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

        myCheckOutAdapter = CheckOutAdapter(myLocalCart.listOfOrder) {
                mealPosition : Int -> myOnClickItem(mealPosition)
        }
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = myCheckOutAdapter
    }

    //Onclick function to pass to recycle items
    private fun myOnClickItem(myMealDetail: Int){
        Toast.makeText(this@CheckOutActivity, "Vous avez cliqué pour suppr !", Toast.LENGTH_SHORT).show()
        //Log.d("##########HUMAN############", message)
        //val myIntent = Intent(this@MealListActivity, MealDetailActivity::class.java)
        //myIntent.putExtra("meal_infos", myMealDetail as Serializable)
        //startActivity(myIntent)
    }
}