package com.zlk.akotlinlearn.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil {

    companion object {
        const val baseUrl = "http://v.juhe.cn/"

//        val retrofitService: RetrofitService =
//            getService(baseUrl, RetrofitService::class.java)

        fun <T> getService(service: Class<T>): T {
            return getService(baseUrl, service)
        }

        /**
         * 获取ServiceApi
         */
        private fun <T> getService(url: String, service: Class<T>): T {
            Log.i("OkHttp: ", "getService()")
            return create(url).create(service)
        }

        private fun create(url: String): Retrofit {
            // 新建log栏截器
            val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
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
            // 添加 全局 Header
//            okHttpClientBuilder.addInterceptor(object: Interceptor {
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val request = chain.request()
//                        .newBuilder()
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader("reqtime-Type", (System.currentTimeMillis() / 1000).toString())
//                        .build()
//                    return chain.proceed(request)
//                }
//            })

            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

}