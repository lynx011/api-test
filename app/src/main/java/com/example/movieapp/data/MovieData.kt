package com.example.movieapp.data

import android.os.Parcel
import android.os.Parcelable

data class MovieData(
    val image: Int,
    val title: String,
    val desc: String,
    val favourite: String,
    val comment: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeString(favourite)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieData> {
        override fun createFromParcel(parcel: Parcel): MovieData {
            return MovieData(parcel)
        }

        override fun newArray(size: Int): Array<MovieData?> {
            return arrayOfNulls(size)
        }
    }
}

private fun Parcel.writeInt(image: String) {
    image.toString()
}
