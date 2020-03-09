package com.p4r4d0x.punkapibeerviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import javax.inject.Inject

class BeerDetailsViewModel @Inject constructor() : ViewModel() {

    private val beerData: MutableLiveData<BeerDTO> = MutableLiveData()

    fun setBeer(beer: BeerDTO) {
        beerData.value = beer
    }

    fun getBeerData(): LiveData<BeerDTO> = beerData


}