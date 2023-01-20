package fr.isen.vincentdubaret.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class PictureAdapter (private val mContext: Context, private  val imageList: ArrayList<String>) : PagerAdapter() {

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_picture, parent, false)
        val imageView: ImageView = view.findViewById(R.id.picture_meal)
        Picasso.with(mContext).load(imageList[position]).into(imageView)
        parent.addView(view, position)
        return view
    }

    override fun getCount(): Int = imageList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, myObject: Any) {
        container.removeView(myObject as View)
    }
}