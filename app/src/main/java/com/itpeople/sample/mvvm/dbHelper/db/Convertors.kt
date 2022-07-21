package com.itpeople.sample.mvvm.dbHelper.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.itpeople.sample.mvvm.data.model.*
import java.util.*

class Convertors {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }


    @TypeConverter
    fun dobToString(dob: Dob): String {
        return Gson().toJson(dob)
    }

    @TypeConverter
    fun stringToDob(value: String): Dob {
        return Gson().fromJson(value, Dob::class.java)
    }

    // ----------------------

    @TypeConverter
    fun pictureToString(pic: Picture): String {
        return Gson().toJson(pic)
    }

    @TypeConverter
    fun stringToPicture(value: String): Picture {
        return Gson().fromJson(value, Picture::class.java)
    }

    // ----------------------

    @TypeConverter
    fun locationToString(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun stringToLocation(value: String): Location {
        return Gson().fromJson(value, Location::class.java)
    }
    // ----------------------

    @TypeConverter
    fun registeredToString(registered: Registered): String {
        return Gson().toJson(registered)
    }

    @TypeConverter
    fun stringToRegistered(value: String): Registered {
        return Gson().fromJson(value, Registered::class.java)
    }
    // ----------------------

    @TypeConverter
    fun nameToString(name: Name): String {
        return Gson().toJson(name)
    }

    @TypeConverter
    fun stringToName(value: String): Name {
        return Gson().fromJson(value, Name::class.java)
    }

}
