package com.p4r4d0x.punkapibeerviewer


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.p4r4d0x.punkapibeerviewer.adapters.BeerAdapter
import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import com.p4r4d0x.punkapibeerviewer.utilities.InjectorUtils
import com.p4r4d0x.punkapibeerviewer.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_search.*


@Suppress("UNCHECKED_CAST")
class SearchFragment : Fragment(), TextWatcher {

    private val viewModel: BeerViewModel by viewModels {
        InjectorUtils.provideBeerListViewModelFactory(requireContext())
    }

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
            val adapter = context?.let {
                BeerAdapter(
                    it, viewModel.getBeerListLiveData().value as ArrayList<BeerDTO>
                )
            }
            lv_beers.adapter = adapter
        }
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

    /**
     * Clear the listView
     */
    private fun clearListView() {
        val adapter = context?.let {
            BeerAdapter(
                it,
                ArrayList()
            )
        }
        lv_beers.adapter = adapter
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() == "") clearListView()
        else viewModel.getBeers(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


}



