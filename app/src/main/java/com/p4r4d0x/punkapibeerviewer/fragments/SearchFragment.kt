package com.p4r4d0x.punkapibeerviewer.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.p4r4d0x.punkapibeerviewer.BeerAdapter
import com.p4r4d0x.punkapibeerviewer.InjectorUtils
import com.p4r4d0x.punkapibeerviewer.MainActivity
import com.p4r4d0x.punkapibeerviewer.R
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.p4r4d0x.punkapibeerviewer.model.BeerViewModel
import kotlinx.android.synthetic.main.fragment_search.*


@Suppress("UNCHECKED_CAST")
class SearchFragment : Fragment(), /*Callback<List<BeerDTO?>?>,*/ TextWatcher {
    private var serviceStatsTimeoutRetries = 0

    /*private val viewModel: BeerViewModel by viewModels(
        factoryProducer = {
            SavedStateViewModelFactory(
                activity!!.application,
                this
            )
    }

    )*/

    private val viewModel: BeerViewModel by viewModels {
        InjectorUtils.provideBeerListViewModelFactory(
            requireContext()
        )
    }

    //private lateinit var viewModel:BeerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //When the ViewModel is changed, set the value to the arrayList
        viewModel.getBeerListLiveData().observe(viewLifecycleOwner) {
            val adapter =
                context?.let {
                    BeerAdapter(
                        it,
                        viewModel.getBeerListLiveData().value as ArrayList<BeerDTO>
                    )
                }
            lv_beers.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
//        btn_random_beer.setOnClickListener { requestRandomBeer() }
        tiet_beer_name.addTextChangedListener(this)
        lv_beers.setOnItemClickListener { parent, view, position, id ->
            (activity as MainActivity).launchDetailFragment(
                (parent.adapter as BeerAdapter).getItem(
                    position
                )
            )

        }
    }

//    /**
//     * Request a random beer against the punkapi backend
//     */
//    private fun requestRandomBeer() {
//        val restClient = RetrofitClient()
//        restClient.requestRandomBeer("https://api.punkapi.com/", this)
//
//    }
//
//    /**
//     * Request a beer with the name that matches textNameBeer
//     */
//    private fun requestBeer(textNameBeer: String) {
//        val restClient = RetrofitClient()
//        restClient.requestBeer("https://api.punkapi.com/", textNameBeer, this)
//    }

    /**
     * Clear the listView
     */
    private fun clearListView() {
        val adapter = context?.let { BeerAdapter(it, ArrayList()) }
        lv_beers.adapter = adapter
    }

//    override fun onFailure(call: Call<List<BeerDTO?>?>, throwableReceived: Throwable) {
//        clearListView()
//        //If its a SocketTimeoutException,SocketException or UnknownHostException (timeout ,bad network or no network) enqueue again the call after 2 seconds of sleeping
//        if ((throwableReceived is SocketTimeoutException || throwableReceived is SocketException || throwableReceived is UnknownHostException)) {
//            //Enqueue a new request max 5 times
//            if (serviceStatsTimeoutRetries < Constants.MAX_SERVICE_TIMEOUT_RETRIES) {
//                call.clone().enqueue(this)
//                Log.d("RetroFit", "StatsService Enqueued: $serviceStatsTimeoutRetries")
//                serviceStatsTimeoutRetries++
//            } else {
//                Log.e(
//                    "RetroFit",
//                    "Beer received onFailure: ${throwableReceived.message}. Too much retries."
//                )
//                Toast.makeText(activity, "Check if you have internet conexion.", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }
//        //Any other exception is treated as an error
//        else {
//            serviceStatsTimeoutRetries = 0
//            Log.e("RetroFit", "Beer received onFailure: ${throwableReceived.message}")
//        }
//
//    }
//
//    override fun onResponse(call: Call<List<BeerDTO?>?>, response: Response<List<BeerDTO?>?>) {
//        Log.d("RetroFit", "StatsService onResponse: " + response.code())
//        serviceStatsTimeoutRetries = 0
//
//        if (response.code() == Constants.SERVER_CONTENT) {
//            val beers: List<BeerDTO?>? = response.body()
//            Log.d(
//                "RetroFit",
//                "Beer Received onResponse: ${response.code()}. Number of beers: " + beers!!.size
//            )
//
//            val adapter = context?.let { BeerAdapter(it, beers as ArrayList<BeerDTO>) }
//            lv_beers.adapter = adapter
//
//
//        } else {
//            clearListView()
//            Log.e("RetroFit", "Beer Received onResponse: " + response.code())
//        }
//    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() == "") clearListView()
        else viewModel.getBeers(s.toString())
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}



