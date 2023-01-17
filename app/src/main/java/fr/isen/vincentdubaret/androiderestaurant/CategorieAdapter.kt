package fr.isen.vincentdubaret.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

internal class CategorieAdapter(var myContext : Context, var myParsedData: ListOfMeal, private val listener: (MealDetail) -> Unit) : RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentName: TextView = view.findViewById(R.id.content_name)
        val contentAllergen : TextView = view.findViewById(R.id.content_allergen)
        val imageMeal : ImageView = view.findViewById(R.id.image_meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myParsedData.items[position]
        holder.contentName.text = item.name_fr
        holder.contentAllergen.text = item.name_fr + " - allergènes"
        Picasso.with(myContext).load(item.images[0]).into(holder.imageMeal)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = myParsedData.items.size
}
