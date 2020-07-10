package com.zlk.akotlinlearn.net

data class BasePageBean<T>(
    val reason: String,
    val result: PageBean<T>,
    val error_code: Int
)

class PageBean<T> {
    // 当前页
    private val pageNum = 0
    // 分页大小
    private val pageSize = 0
    // 总条数
    private val total = 0
    // 总页数
    private val totalPage = 0
    private val datas: List<T>? = null
}
