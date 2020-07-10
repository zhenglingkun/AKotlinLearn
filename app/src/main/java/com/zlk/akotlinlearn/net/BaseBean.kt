package com.zlk.akotlinlearn.net

data class BaseBean<T>(
    val reason: String,
    val result: T,
    val error_code: Int
)