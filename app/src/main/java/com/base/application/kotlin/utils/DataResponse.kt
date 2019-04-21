package com.base.application.kotlin.utils

interface DataResponse<T> {
    fun retrieveData(): T
}