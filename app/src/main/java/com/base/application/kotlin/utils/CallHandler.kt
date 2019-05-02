package com.base.application.kotlin.utils

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response

class CallHandler<RESPONSE : Any, DATA: Any> {
    lateinit var client: Deferred<Response<RESPONSE>>

    fun makeCall() : MutableLiveData<Resource<DATA>> {
        val result = MutableLiveData<Resource<DATA>>()
        result.value = Resource.loading(null)
        GlobalScope.launch {
            try {
                val response = client.await() as DataResponse<DATA>
                withContext(Dispatchers.Main) { result.value = Resource.success(response.retrieveData()) }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    if (e is HttpException)
                        result.value = Resource.error("${e.message} | code ${e.response().code()}", null)
                    else
                        result.value = Resource.error("${e.message}", null)
                }
                e.printStackTrace()
            }
        }
        return result
    }
}