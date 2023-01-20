package fr.isen.vincentdubaret.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class CheckOutAdapter(var arrayOrder: ArrayList<AnOrder>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<CheckOutAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentName: TextView = view.findViewById(R.id.checkout_content_name)
        val contentPrice : TextView = view.findViewById(R.id.checkout_content_price)
        val contentQty : TextView = view.findViewById(R.id.checkout_content_qty)
        val imageTrash : ImageView = view.findViewById(R.id.checkout_trash)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkout, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = arrayOrder[position]
        holder.contentName.text = item.mealName
        holder.contentPrice.text = item.mealPrice.toString()
        holder.contentQty.text = item.mealQuantity.toString()
        holder.imageTrash.setOnClickListener { listener(item.mealId) }
    }

    override fun getItemCount(): Int = arrayOrder.size


}
