package com.zlk.akotlinlearn.net

import android.util.Log
import com.zlk.akotlinlearn.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil {

    companion object {
        private fun create(url: String): Retrofit {
            // 新建log栏截器
            val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i("netWork", "net = $message")
                }
            })
            // 日志显示级别
            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            loggingInterceptor.level = level

            val okHttpClientBuilder = OkHttpClient().newBuilder()
            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)

            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val retrofitService: RetrofitService =
            getService(Constants.REQUEST_BASE_URL, RetrofitService::class.java)

        /**
         * 获取ServiceApi
         */
        private fun <T> getService(url: String, service: Class<T>): T {
            Log.i("OkHttp: ", "getService()")
            return create(url).create(service)
        }
    }

}