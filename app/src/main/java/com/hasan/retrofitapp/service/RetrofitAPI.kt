package com.hasan.retrofitapp.service

import com.hasan.retrofitapp.model.Model
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitAPI {

    /**
     * Gets data from API.
     *
     * @return [Single<List<Model>>].
     */
    @GET("realestate")
    fun getFieldsMars(): Single<List<Model>>
}