package com.example.mobilecodingchallenge.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilecodingchallenge.R
import com.squareup.picasso.Picasso

class ImageAdapter(private val images: List<Image>, private val clickListener: (Image) -> Unit) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image, clickListener)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image, clickListener: (Image) -> Unit) {
            itemView.findViewById<TextView>(R.id.image_title).text = image.title
            Picasso.get().load(image.urls.regular).into(itemView.findViewById<ImageView>(R.id.image_view))
            itemView.setOnClickListener { clickListener(image) }
        }
    }
}
