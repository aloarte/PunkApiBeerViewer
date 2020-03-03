package com.p4r4d0x.punkapibeerviewer.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.p4r4d0x.punkapibeerviewer.R
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.squareup.picasso.Picasso

class BeerAdapter(context: Context, private val dataSource: ArrayList<BeerDTO>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_beer, parent, false)

        //Initialize the views of the adapter
        val tvBeerName = rowView.findViewById(R.id.tv_beer_name) as TextView
        val tvBeerTagline = rowView.findViewById(R.id.tv_beer_tagline) as TextView
        val thumbnailImageView = rowView.findViewById(R.id.iv_beer_image) as ImageView

        //Get the value of the item on the list and place its value on the views
        val beerData = getItem(position)
        tvBeerName.text = beerData.name
        tvBeerTagline.text = beerData.tagline
        Picasso.get().load(beerData.imageUrl).placeholder(R.mipmap.beermug)
            .into(thumbnailImageView)



        return rowView
    }

    override fun getItem(position: Int): BeerDTO {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}