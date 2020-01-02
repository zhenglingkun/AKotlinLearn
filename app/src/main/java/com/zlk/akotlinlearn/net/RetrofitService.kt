package com.zlk.akotlinlearn.net

import com.zlk.akotlinlearn.bean.CalendarDayBean
import com.zlk.akotlinlearn.bean.CalenderDayData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    /**
     * 获取当天详细信息
     *
     * @param date 日期
     */
    @GET("calendar/day")
    fun calenderDay(
        @Query("date") date: String,
        @Query("key") key: String
    ): Observable<CalendarDayBean<CalenderDayData>>

}