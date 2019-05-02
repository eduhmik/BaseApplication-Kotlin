package com.base.application.kotlin.model.data

import com.base.application.kotlin.utils.DataResponse

data class FooResponse(val data: List<Foo>): BaseApiResponse(), DataResponse<List<Foo>> {
    override fun retrieveData(): List<Foo> = data
}

abstract class BaseApiResponse {
    var status: Int = 0
    var message: String? = null
}