//package ru.anyname.myapplication.data.models
//
//import android.os.Parcel
//import android.os.Parcelable
//
//data class Movie(
//
//    val cover: String?,
//    val ageLimit: String?,
//    val like: String?,
//    val rating1: String?,
//    val rating2: String?,
//    val rating3: String?,
//    val rating4: String?,
//    val rating5: String?,
//    val movieGenre: String?,
//    val reviews: String?,
//    val movieTitle: String?,
//    val movieDuration: String?,
//
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString())
//
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(cover)
//        parcel.writeString(ageLimit)
//        parcel.writeString(like)
//        parcel.writeString(rating1)
//        parcel.writeString(rating2)
//        parcel.writeString(rating3)
//        parcel.writeString(rating4)
//        parcel.writeString(rating5)
//        parcel.writeString(movieGenre)
//        parcel.writeString(reviews)
//        parcel.writeString(movieTitle)
//        parcel.writeString(movieDuration)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
//
//data class Actor(
//    val actorName: String?,
//    val actorAvatar: String?
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString()) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(actorName)
//        parcel.writeString(actorAvatar)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Actor> {
//        override fun createFromParcel(parcel: Parcel): Actor {
//            return Actor(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Actor?> {
//            return arrayOfNulls(size)
//        }
//    }
//}