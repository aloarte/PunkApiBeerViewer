package com.p4r4d0x.punkapibeerviewer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.p4r4d0x.punkapibeerviewer.Constants.MAX_SERVICE_TIMEOUT_RETRIES
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(), Callback<List<BeerDTO?>?> {

    private var serviceStatsTimeoutRetries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //requestRandomBeer()
    }

    override fun onResume() {
        super.onResume()
        btn_random_beer.setOnClickListener { requestRandomBeer() }
    }

    fun requestRandomBeer() {
        val restClient = RetrofitClient()
        restClient.requestRandomBeer("https://api.punkapi.com/", this)


    }

    override fun onFailure(call: Call<List<BeerDTO?>?>, throwableReceived: Throwable) {
        //If its a SocketTimeoutException,SocketException or UnknownHostException (timeout ,bad network or no network) enqueue again the call after 2 seconds of sleeping
        if ((throwableReceived is SocketTimeoutException || throwableReceived is SocketException || throwableReceived is UnknownHostException)) {
            //Enqueue a new request max 5 times
            if (serviceStatsTimeoutRetries < MAX_SERVICE_TIMEOUT_RETRIES) {
                call.clone().enqueue(this)
                Log.d("RetroFit", "StatsService Enqueued: $serviceStatsTimeoutRetries")
                serviceStatsTimeoutRetries++
            } else {
                //TODO: Notify an error
            }
        }
        //Any other exception is treated as an error
        else {
            serviceStatsTimeoutRetries = 0
            Log.e("RetroFit", "Beer received onFailure: " + throwableReceived.message)
        }

    }

    override fun onResponse(call: Call<List<BeerDTO?>?>, response: Response<List<BeerDTO?>?>) {
        Log.d("RetroFit", "StatsService onResponse: " + response.code())
        serviceStatsTimeoutRetries = 0

        if (response.code() == Constants.SERVER_CONTENT) {
            val beers: List<BeerDTO?>? = response.body()
            Log.d(
                "RetroFit",
                "Beer Received onResponse: ${response.code()}. Number of beers: " + beers!!.size
            )

            val adapter = BeerAdapter(this, beers as ArrayList<BeerDTO>)
            lv_beers.adapter = adapter


        } else {
            Log.e("RetroFit", "Beer Received onResponse: " + response.code())
        }
    }
}
