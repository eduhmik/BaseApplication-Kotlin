package com.base.application.kotlin.model.rest

import com.base.application.kotlin.model.data.FooResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface FooRestApi {
    @GET("todo/list")
    fun getFooList(): Deferred<Response<FooResponse>>
}