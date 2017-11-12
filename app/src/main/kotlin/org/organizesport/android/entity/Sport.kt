package org.organizesport.android.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * This class refers to the 'Entity' layer (VIPER architecture) to be used across the app,
 * specifically to the 'Sport' model.
 *
 * @author pablol.
 * @since 1.1
 */
data class Sport(internal val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Sport> {
        override fun createFromParcel(parcel: Parcel): Sport = Sport(parcel)

        override fun newArray(size: Int): Array<Sport?> = arrayOfNulls(size)
    }
}
