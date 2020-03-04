package com.p4r4d0x.punkapibeerviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.p4r4d0x.punkapibeerviewer.model.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BeerViewModel @Inject constructor(private val beerRepository: BeerRepository) : ViewModel() {

    private val beerListData: MutableLiveData<List<BeerDTO>> = MutableLiveData()

    fun getBeers(beerName: String) = viewModelScope.launch(Dispatchers.IO) {
        val values = beerRepository.getBeers(beerName)
        beerListData.postValue(values)
    }

    fun getBeerListLiveData(): LiveData<List<BeerDTO>> {
        return beerListData
    }

    fun eraseAllBeers() = viewModelScope.launch(Dispatchers.IO) {
        beerRepository.deleteBeers()
        beerListData.postValue(ArrayList())

    }

}