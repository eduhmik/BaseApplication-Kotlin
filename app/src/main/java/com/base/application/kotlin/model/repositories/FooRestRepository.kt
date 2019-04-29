package com.base.application.kotlin.model.repositories

import androidx.lifecycle.MutableLiveData
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.data.FooResponse
import com.base.application.kotlin.model.rest.FooRestApi
import com.base.application.kotlin.utils.CallHandler
import com.base.application.kotlin.utils.DataResponse
import com.base.application.kotlin.utils.Resource

class FooRestRepository(var fooRestApi: FooRestApi) {

    fun getFooList() = networkCall<FooResponse, List<Foo>> {
        client = fooRestApi.getFooList()
    }

    fun <RESPONSE: DataResponse<*>, DATA: Any> networkCall(block: CallHandler<RESPONSE, DATA>.() -> Unit):
            MutableLiveData<Resource<DATA>> = CallHandler<RESPONSE, DATA>().apply(block).makeCall()
}


