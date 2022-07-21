package com.itpeople.sample.mvvm.dbHelper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.itpeople.sample.mvvm.dbHelper.dao.ResultDao
import com.itpeople.sample.mvvm.utilities.DATABASE_VERSION


@Database(
    entities = [ResultsItem::class],
    version = DATABASE_VERSION
)

@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}