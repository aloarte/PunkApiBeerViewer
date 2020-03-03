package com.p4r4d0x.punkapibeerviewer.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.p4r4d0x.punkapibeerviewer.model.BeerRepository

/**
 * Factory for creating a [BeerViewModelFactory] with a constructor that takes a [BeerRepository].
 */
class BeerViewModelFactory(private val repository: BeerRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeerViewModel(repository) as T
    }
}
