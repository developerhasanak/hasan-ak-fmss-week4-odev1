package com.hasan.retrofitapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hasan.retrofitapp.model.Model


@Database(entities = arrayOf(Model::class), version = 1)
abstract class RetrofitDatabase : RoomDatabase() {

    abstract fun retrofitDao(): RetrofitDao

    companion object {

        @Volatile
        private var instance: RetrofitDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        /**
         * This method creates database.
         *
         * @param context onRecyclerItemClick makeDatabase.
         * @return this function return  [ Room.databaseBuilder()].
         */
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, RetrofitDatabase::class.java, "retrofitdatabase"
        ).build()
    }

}