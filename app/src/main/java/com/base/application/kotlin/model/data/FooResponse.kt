package com.base.application.kotlin.model.data

data class FooResponse(val data: List<Foo>): BaseApiResponse()

abstract class BaseApiResponse {
    var status: Int = 0
    var message: String? = null
}