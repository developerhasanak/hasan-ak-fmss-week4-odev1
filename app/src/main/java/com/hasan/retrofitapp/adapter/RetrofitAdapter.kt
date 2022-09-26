package com.hasan.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hasan.retrofitapp.R
import com.hasan.retrofitapp.databinding.RecyclerRowItemBinding
import com.hasan.retrofitapp.model.Model
import com.hasan.retrofitapp.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_row_item.view.*

class RetrofitAdapter(var modelList: List<Model>) :
    RecyclerView.Adapter<RetrofitAdapter.RetrofitViewHolder>(), RecyclerItemClickListener {

    class RetrofitViewHolder(val binding: RecyclerRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerRowItemBinding>(
            inflater,
            R.layout.recycler_row_item,
            parent,
            false
        )
        return RetrofitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {

        holder.binding.model = modelList[position]
        holder.binding.clickListener = this
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onRecyclerItemClick(v: View) {
        val uuid = v.fieldUudi.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment()
        action.filedUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }
}