package com.p4r4d0x.punkapibeerviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchHomeFragment()
    }

    private fun launchHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_fragment,
            HomeFragment()
        ).commit()
    }

    fun launchSearchFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_fragment,
            SearchFragment()
        ).commit()
    }

    fun launchDetailFragment(beerData: BeerDTO) {
        val detailsFragment = DetailsFragment()
        detailsFragment.beer = beerData
        supportFragmentManager.beginTransaction().replace(R.id.nav_fragment, detailsFragment)
            .commit()
    }

}
