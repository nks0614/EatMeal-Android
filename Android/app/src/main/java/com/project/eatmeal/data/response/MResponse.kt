package com.project.eatmeal.data.response

data class MResponse<T> (
    val status: Int,
    val message: String,
    val data : T
)

data class MResponseNoData(
    val status: Int,
    val message: String,
)