package com.itpeople.sample.mvvm.dbHelper.dao

import androidx.room.*
import com.itpeople.sample.mvvm.data.model.ResultsItem
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: List<ResultsItem?>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: ResultsItem): Completable

    @Update
    fun update(result: ResultsItem): Completable

    @Query("delete from table_results")
    fun deleteAll(): Completable

    @Query("Select * from table_results")
    fun getUserData(): Single<List<ResultsItem>>
}