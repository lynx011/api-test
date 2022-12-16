package com.example.movieapp.model

import android.os.Parcel
import android.os.Parcelable

data class ArticleModel(
//    val source: SourceModel,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString().toString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
//        parcel!!.writeString(source.toString())
        parcel!!.writeString(author)
        with(parcel) {
            writeString(title)
            writeString(description)
            writeString(url)
            writeString(urlToImage)
            writeString(publishedAt)
            writeString(content)
        }
    }

    companion object CREATOR : Parcelable.Creator<ArticleModel> {
        override fun createFromParcel(parcel: Parcel): ArticleModel {
            return ArticleModel(parcel)
        }

        override fun newArray(size: Int): Array<ArticleModel?> {
            return arrayOfNulls(size)
        }
    }
}

//data class SourceModel(
//    val id : String,
//    val name : String,
//)