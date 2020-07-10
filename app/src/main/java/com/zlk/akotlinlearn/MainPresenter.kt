package com.zlk.akotlinlearn

import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.zlk.akotlinlearn.common.Constants
import com.zlk.akotlinlearn.net.RetrofitService
import com.zlk.akotlinlearn.net.RetrofitUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainPresenter constructor(@NonNull val view: MainContract.View) : MainContract.Presenter {

    private val mDisposable: CompositeDisposable = CompositeDisposable()

    private val retrofitService: RetrofitService =
        RetrofitUtil.getService(RetrofitService::class.java)

    init {
        view.setPresenter(this)
    }

    override fun onStart() {
        val c: Calendar = Calendar.getInstance()
        val date =
            "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DAY_OF_MONTH)}"
        val d: Disposable = retrofitService
            .calenderDay(date, Constants.KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (view.isActive) {
                    view.refreshComplete()
                    var date: String = result.result.data.date
                    var dates = date.split("-")
                    view.showDate(dates)
                    view.showWeekday(result.result.data.weekday)
                    view.showLunar(result.result.data.lunar)
                    view.showLunarYear(result.result.data.lunarYear)
                    view.showAnimalsYear(result.result.data.animalsYear)
                    if (!TextUtils.isEmpty(result.result.data.holiday)) {
                        view.showHoliday(result.result.data.holiday, result.result.data.desc)
                    } else {
                        view.hideHoliday()
                    }
                    view.showSuite(result.result.data.suit)
                    view.showAvoid(result.result.data.avoid)
                }
//                Log.e("Main", result.result.data.toString())
            }, { error ->
//                Log.e("Main", error.message.toString())
                Log.wtf("Main", error.message.toString())
            })
        mDisposable.add(d)
    }

    override fun onDestroy() {
        mDisposable.dispose()
    }
}