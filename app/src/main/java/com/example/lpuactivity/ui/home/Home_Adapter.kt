package com.example.lpuactivity.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lpuactivity.util.MainActivity2
import com.example.lpuactivity.R
import com.example.lpuactivity.models.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recycle_home.view.*

class Home_Adapter(private val homefeed: List<Video>):
    RecyclerView.Adapter<Home_Adapter.ExampleViewHolder>() {

    inner class ExampleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){




    init {
        itemView.setOnClickListener {
            val position:Int =adapterPosition
            val video=homefeed[position]
            val intent = Intent (itemView.context, MainActivity2::class.java)

            intent.putExtra("Title",video.title)
            intent.putExtra("description",video.description)
            intent.putExtra("Image",video.photos)
            intent.putExtra("Postby",video.postby)
            intent.putExtra("Image",video.photos)
            intent.putExtra("Price",video.price)
            intent.putExtra("Description",video.description)
            intent.putExtra("Id",video.id)
            itemView.context.startActivity(intent)
            Toast.makeText(itemView.context,"${video.price}",Toast.LENGTH_SHORT).show()

        }
    }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val cellofrow=layoutInflater.inflate(R.layout.fragment_recycle_home,parent,false)
        return ExampleViewHolder(cellofrow)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {

        val video=homefeed[position]
        holder.itemView.text_home.text=video.title
            val imageholder=holder.itemView.image_home
        Picasso.get().load(video.photos).into(imageholder)

    }

    override fun getItemCount(): Int {

        return homefeed.count()
    }


}