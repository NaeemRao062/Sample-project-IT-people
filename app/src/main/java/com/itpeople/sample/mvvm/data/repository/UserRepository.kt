package com.itpeople.sample.mvvm.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.itpeople.sample.mvvm.data.api.ApiService
import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.itpeople.sample.mvvm.data.model.UserResponse
import com.itpeople.sample.mvvm.dbHelper.db.AppDatabase
import com.itpeople.sample.mvvm.utilities.DebugHelperUtility
import com.itpeople.sample.mvvm.utils.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val compositeDisposable: CompositeDisposable,
    private val apiService: ApiService
) {

    suspend fun getUser() : Response<UserResponse> {
        return apiService.getUserResults(1,100)
    }

    fun insertUser(users: List<ResultsItem?>): MutableLiveData<Status> {

        val insertSuccess: MutableLiveData<Status> = MutableLiveData()

        val completable = appDatabase.resultDao().insert(users)
            .andThen(appDatabase.resultDao().insert(users))

        val disposable = completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                insertSuccess.value = Status.SUCCESS
                Log.i("DbInsertion", "Success")
            }, {
                insertSuccess.value = Status.ERROR
                deleteAllData()
            })
        compositeDisposable.add(disposable)

        return insertSuccess
    }

    fun fetchUsers(): MutableLiveData<List<ResultsItem>> {

        val mutableLiveData: MutableLiveData<List<ResultsItem>> = MutableLiveData()
        val disposable = appDatabase.resultDao().getUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                mutableLiveData.value = users
                DebugHelperUtility.printInfo("Users select data successfully")

            }, {
                DebugHelperUtility.printError("Users select ", Error(it))
            })

        compositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun deleteAllData() {

        val disposable = appDatabase.resultDao().deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                DebugHelperUtility.printInfo("dbDeletion", "Success")
            }, {
                DebugHelperUtility.printError("dbDeletion", Error(it))
            })
        compositeDisposable.add(disposable)
    }

}