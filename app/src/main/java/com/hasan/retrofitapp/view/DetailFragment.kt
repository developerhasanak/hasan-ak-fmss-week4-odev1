package com.hasan.retrofitapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.hasan.retrofitapp.databinding.FragmentDetailBinding
import com.hasan.retrofitapp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var fieldId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment.
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Here from the feed fragment detail fragment data is transferred.
        arguments?.let {
            fieldId = DetailFragmentArgs.fromBundle(it).filedUuid
        }

        //Here, the detail provides integration between the fragment view model.
        viewModel = ViewModelProviders.of(this@DetailFragment)[DetailViewModel::class.java]
        viewModel.getDataFromRoom(fieldId)

        observeLiveData()

        //here pressing the back button in the upper left corner returns the feed in the trailer.
        binding.detailBackButton.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToFeedFragment()
            Navigation.findNavController(it).navigate(action)
        }

        // Click the button here and you will be redirected to google.
        binding.buyNowButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(intent)
        }
    }

    // Here the LiveData created by the detail view model is observed.
    private fun observeLiveData() {
        viewModel.fieldLiveData.observe(viewLifecycleOwner, Observer { field ->
            field?.let {
                binding.selectField = field
            }
        })
    }
}