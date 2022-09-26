package com.hasan.retrofitapp.adapter

import android.view.View

/**
 * This UI adds ability to click item in recyclerview.
 *
 */
interface RecyclerItemClickListener {
    /**
     *@param v is a value for onRecyclerItemClick.
     */
    fun onRecyclerItemClick(v: View)
}