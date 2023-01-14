package com.example.ktogdziekiedy

import android.os.Parcel
import android.os.Parcelable

data class ItemsViewModel(val text: String?): Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsViewModel> {
        override fun createFromParcel(parcel: Parcel): ItemsViewModel {
            return ItemsViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsViewModel?> {
            return arrayOfNulls(size)
        }
    }
}