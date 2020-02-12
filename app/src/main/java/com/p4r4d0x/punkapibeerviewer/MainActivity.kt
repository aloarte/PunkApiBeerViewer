package com.p4r4d0x.punkapibeerviewer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
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
class MainActivity : AppCompatActivity(), Callback<List<BeerDTO?>?>, TextWatcher {

    private var serviceStatsTimeoutRetries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        btn_random_beer.setOnClickListener { requestRandomBeer() }
        tiet_beer_name.addTextChangedListener(this)
    }

    /**
     * Request a random beer agains the punkapi backend
     */
    private fun requestRandomBeer() {
        val restClient = RetrofitClient()
        restClient.requestRandomBeer("https://api.punkapi.com/", this)

    }

    /**
     * Request a beer with the name that matches textNameBeer
     */
    private fun requestBeer(textNameBeer: String) {
        val restClient = RetrofitClient()
        restClient.requestBeer("https://api.punkapi.com/", textNameBeer, this)
    }

    /**
     * Clear the listView
     */
    private fun clearListView() {
        val adapter = BeerAdapter(this, ArrayList())
        lv_beers.adapter = adapter
    }

    override fun onFailure(call: Call<List<BeerDTO?>?>, throwableReceived: Throwable) {
        clearListView()
        //If its a SocketTimeoutException,SocketException or UnknownHostException (timeout ,bad network or no network) enqueue again the call after 2 seconds of sleeping
        if ((throwableReceived is SocketTimeoutException || throwableReceived is SocketException || throwableReceived is UnknownHostException)) {
            //Enqueue a new request max 5 times
            if (serviceStatsTimeoutRetries < MAX_SERVICE_TIMEOUT_RETRIES) {
                call.clone().enqueue(this)
                Log.d("RetroFit", "StatsService Enqueued: $serviceStatsTimeoutRetries")
                serviceStatsTimeoutRetries++
            } else {
                Log.e(
                    "RetroFit",
                    "Beer received onFailure: ${throwableReceived.message}. Too much retries."
                )
                Toast.makeText(this, "Check if you have internet conexion.", Toast.LENGTH_LONG)
                    .show()
            }
        }
        //Any other exception is treated as an error
        else {
            serviceStatsTimeoutRetries = 0
            Log.e("RetroFit", "Beer received onFailure: ${throwableReceived.message}")
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
            clearListView()
            Log.e("RetroFit", "Beer Received onResponse: " + response.code())
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() == "") clearListView()
        else requestBeer(s.toString())
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}
