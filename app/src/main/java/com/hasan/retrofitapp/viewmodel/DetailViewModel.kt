package com.hasan.retrofitapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hasan.retrofitapp.database.RetrofitDatabase
import com.hasan.retrofitapp.model.Model
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val fieldLiveData = MutableLiveData<Model>()

    /**
     * This method creates a database and performs Dao operations asynchronously with the help of Coroutine.
     *
     * @param uuid is a value for getDataFromRoom
     */
    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = RetrofitDatabase(getApplication()).retrofitDao()
            val field = dao.getField(uuid)
            fieldLiveData.value = field
        }
    }
}