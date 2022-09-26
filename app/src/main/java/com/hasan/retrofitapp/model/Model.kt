package com.hasan.retrofitapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Model(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int?,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?,
    @ColumnInfo(name = "img_src")
    @SerializedName("img_src")
    val img_src: String?
)
