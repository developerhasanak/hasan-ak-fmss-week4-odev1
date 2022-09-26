package com.hasan.retrofitapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hasan.retrofitapp.database.RetrofitDatabase
import com.hasan.retrofitapp.model.Model
import com.hasan.retrofitapp.service.RetrofitAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val retrofitAPIService = RetrofitAPIService()
    private val disposable = CompositeDisposable()

    val fieldsMars = MutableLiveData<List<Model>>()

    /**
     * This methodWith the help of RxJava2 module,
     * it gets data from api asynchronously.
     *
     */
    fun getDataFromApi() {

        disposable.add(
            retrofitAPIService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {

                    override fun onSuccess(t: List<Model>) {
                        fieldsMars.value = t
                        storeInSQlite(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    /**
     *This method is from apithe captured dataImports local database.
     *
     * @param list is a value for storeInSQlite
     */
    private fun storeInSQlite(list: List<Model>) {
        launch {
            val dao = RetrofitDatabase(getApplication()).retrofitDao()
            dao.deleteAllFields()
            dao.insertAll(*list.toTypedArray())
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}