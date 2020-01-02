package com.zlk.akotlinlearn

import android.util.Log
import androidx.annotation.NonNull
import com.zlk.akotlinlearn.common.Constants
import com.zlk.akotlinlearn.net.RetrofitUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainPresenter constructor(@NonNull val view: MainContract.View) : MainContract.Presenter {

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    init {
        view.setPresenter(this)
    }

    override fun onStart() {
        val c: Calendar = Calendar.getInstance()
        val date =
            "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DAY_OF_MONTH)}"
        val d: Disposable = RetrofitUtil
            .retrofitService
            .calenderDay(date, Constants.KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (view.isActive) {
                    view.showSuite(result.result.data.suit)
                }
                Log.e("Main", result.result.data.toString())
            }, { error ->
                Log.e("Main", error.message.toString())
            })
        mDisposable.add(d)
    }

    override fun onDestroy() {
        mDisposable.dispose()
    }
}