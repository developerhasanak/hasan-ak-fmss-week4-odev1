package com.hasan.retrofitapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.hasan.retrofitapp.BR
import com.hasan.retrofitapp.adapter.RetrofitAdapter
import com.hasan.retrofitapp.databinding.FragmentFeedBinding
import com.hasan.retrofitapp.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private var retrofitAdapter = RetrofitAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Here, the feed provides integration between the fragment view model.
        viewModel = ViewModelProviders.of(this@FeedFragment)[FeedViewModel::class.java]
        viewModel.getDataFromApi()

        //Here, the recycler
        //The layout type of the view is determined and the decoration is added for the items
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.addItemDecoration(SpacesItemDecoration(10))

        observerLiveData()
    }

    // Here the LiveData created by the feed view model is observed.
    private fun observerLiveData() {
        viewModel.fieldsMars.observe(viewLifecycleOwner, Observer { fields ->
            fields?.let {
                binding.setVariable(BR.adapter, retrofitAdapter)

                //Here, the list that the adapter expects is assigned.
                retrofitAdapter.modelList = fields
            }

        })
    }
}