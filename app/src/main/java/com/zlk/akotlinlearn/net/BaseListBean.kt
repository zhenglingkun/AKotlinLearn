package com.zlk.akotlinlearn.net

data class BaseListBean<T>(
    val reason: String,
    val result: List<T>,
    val error_code: Int
)