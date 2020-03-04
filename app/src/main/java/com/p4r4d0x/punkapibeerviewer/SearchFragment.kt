package com.p4r4d0x.punkapibeerviewer


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.p4r4d0x.punkapibeerviewer.adapters.BeerAdapter
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.p4r4d0x.punkapibeerviewer.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class SearchFragment : Fragment(), TextWatcher {
    @Inject
    lateinit var beerViewModel: BeerViewModel

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
        beerViewModel.getBeerListLiveData().observe(viewLifecycleOwner) {
            val adapter = context?.let {
                BeerAdapter(
                    it,
                    beerViewModel.getBeerListLiveData().value as ArrayList<BeerDTO>
                )
            }
            lv_beers.adapter = adapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as PunkapiBeerViewerApplication).appComponent.inject(
            this
        )
    }

    override fun onResume() {
        super.onResume()
        tiet_beer_name.addTextChangedListener(this)
        lv_beers.setOnItemClickListener { parent, _, position, _ ->
            (activity as MainActivity).launchDetailFragment(
                (parent.adapter as BeerAdapter).getItem(
                    position
                )
            )
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() == "") /*clearListView()*/ beerViewModel.eraseAllBeers()
        else beerViewModel.getBeers(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}



