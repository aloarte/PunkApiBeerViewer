package com.p4r4d0x.punkapibeerviewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.p4r4d0x.punkapibeerviewer.viewmodel.BeerDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var beerDetailsViewModel: BeerDetailsViewModel


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

        (requireActivity().applicationContext as PunkapiBeerViewerApplication).appComponent.inject(
            this
        )

        //Set the data on the view model, checking of there is any data from the saved instance state
        if (savedInstanceState == null) {
            beerDetailsViewModel.setBeer(beer)
        }

        //Observe the viewmodel
        beerDetailsViewModel.getBeerData().observe(viewLifecycleOwner, Observer {


            tv_beer_name.text = it.name
            tv_beer_tagline.text = it.tagline
            tv_beer_description.text = it.description
            tv_abv.text = "Vol. : ${it.abv}"
            tv_ibu.text = "Ibu: ${it.ibu}"
            tv_ph.text = "PH: ${it.ph}"
            tv_brewer_tips.text = "Brewer tip: ${it.brewersTips}"
            //Get the image from the URL
            Picasso.get().load(it.imageUrl).placeholder(R.mipmap.beermug)
                .into(iv_beer_image)

        })

        //Set the onclick event for the button
        btn_back_to_search.setOnClickListener { requireActivity().onBackPressed() }

    }


}
