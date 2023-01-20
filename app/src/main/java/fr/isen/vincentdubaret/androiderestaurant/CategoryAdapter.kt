package fr.isen.vincentdubaret.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


internal class CategoryAdapter(var myContext : Context, var myParsedData: ListOfMeal, private val listener: (MealDetail) -> Unit) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentName: TextView = view.findViewById(R.id.content_name)
        val contentPrice : TextView = view.findViewById(R.id.content_price)
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
        holder.contentPrice.text = item.prices[0].price.toString()
        val url = item.images[0]
        if (url.isNotEmpty()) {
            if (item.images[0] != "") {
                Picasso.with(myContext).load(item.images[0])
                    .error(R.drawable.not_found)
                    .into(holder.imageMeal)
            } else {
                holder.imageMeal.setImageResource(R.drawable.not_found)
            }
        } else {
            holder.imageMeal.setImageResource(R.drawable.not_found)
        }
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = myParsedData.items.size
}
