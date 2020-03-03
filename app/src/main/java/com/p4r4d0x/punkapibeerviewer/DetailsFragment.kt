package com.p4r4d0x.punkapibeerviewer


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    lateinit var beer: BeerDTO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_beer_name.text = beer.name
        tv_beer_tagline.text = beer.tagline
        tv_beer_description.text = beer.description
        tv_abv.text = "Vol. : ${beer.abv}"
        tv_ibu.text = "Ibu: ${beer.ibu}"
        tv_ph.text = "PH: ${beer.ph}"
        tv_brewer_tips.text = "Brewer tip: ${beer.brewersTips}"
        //Get the image from the URL
        Picasso.get().load(beer.imageUrl).placeholder(R.mipmap.beermug)
            .into(iv_beer_image)

        //Set the onclick event for the button
        btn_back_to_search.setOnClickListener { (activity as MainActivity).launchSearchFragment() }
    }
}
