package com.hasan.retrofitapp.service

import com.hasan.retrofitapp.model.Model
import com.hasan.retrofitapp.util.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RetrofitAPI::class.java)

    /**
     * Gets data from APIService
     *
     * @return [Single<List<Model>>].
     */
    fun getData(): Single<List<Model>> {
        return api.getFieldsMars()
    }
}