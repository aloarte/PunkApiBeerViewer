package com.p4r4d0x.punkapibeerviewer

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.p4r4d0x.punkapibeerviewer.Constants.MAX_SERVICE_TIMEOUT_RETRIES
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainActivity : AppCompatActivity(), Callback<List<BeerDTO?>?> {

    private var serviceStatsTimeoutRetries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        requestRandomBeer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
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

        } else {
            Log.e("RetroFit", "Beer Received onResponse: " + response.code())
        }
    }
}
