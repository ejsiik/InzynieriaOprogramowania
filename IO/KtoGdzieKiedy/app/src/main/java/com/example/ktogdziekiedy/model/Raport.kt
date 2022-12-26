package com.example.ktogdziekiedy.model

import android.os.Parcel
import android.os.Parcelable

data class Raport(var title: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Raport> {
        override fun createFromParcel(parcel: Parcel): Raport {
            return Raport(parcel)
        }

        override fun newArray(size: Int): Array<Raport?> {
            return arrayOfNulls(size)
        }
    }
}
