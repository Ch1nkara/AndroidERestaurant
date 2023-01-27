package fr.isen.vincentdubaret.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.vincentdubaret.androiderestaurant.databinding.ActivityCheckOutBinding
import java.io.File
import java.io.IOException

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

        var readContent = ""
        val file = File(this.filesDir, "localCart.txt")
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

    //Onclick function to pass to recycle items - remove item from cart
    private fun myOnClickItem(positionInCart: Int){
        myLocalCart.listOfOrder.removeAt(positionInCart)
        myCheckOutAdapter = CheckOutAdapter(myLocalCart.listOfOrder) {
                mealPosition : Int -> myOnClickItem(mealPosition)
        }
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = myCheckOutAdapter
        val file = File(this.filesDir, "localCart.txt")
        try {
            file.delete()
            file.writeText(Gson().toJson(myLocalCart))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}