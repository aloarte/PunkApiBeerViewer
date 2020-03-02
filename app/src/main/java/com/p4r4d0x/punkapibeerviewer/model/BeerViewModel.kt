package com.p4r4d0x.punkapibeerviewer.model

import androidx.lifecycle.*
import com.p4r4d0x.punkapibeerviewer.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeerViewModel(private val beerRepository: BeerRepository) : ViewModel() {

    //private val beerRepository: BeerRepository = BeerRepository(BeerDatabase.getInstance(application).beerDao())

    private val beerListData: MutableLiveData<List<BeerDTO>> = MutableLiveData()

    val user: LiveData<List<BeerDTO>> = liveData {
        val data = beerRepository.getInitBeerRepositoryData()
        emit(data)
    }


    init {
        //Initialize the BeerRepository with the BeerDatabase instance and the BeerDao
        //Initialize the list of beers
        beerListData.value = beerRepository.getInitBeerRepositoryData()
    }

    fun getBeers(beerName: String) = viewModelScope.launch(Dispatchers.IO) {
        val values = beerRepository.getBeers(beerName)
        beerListData.postValue(values)
    }

    fun getBeerListLiveData(): LiveData<List<BeerDTO>> {
        return beerListData
    }

}