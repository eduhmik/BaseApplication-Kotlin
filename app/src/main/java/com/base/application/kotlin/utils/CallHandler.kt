package com.base.application.kotlin.utils

import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.*
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
                withContext(Dispatchers.Main) { result.value = Resource.error(e.localizedMessage, null) }
            }
        }
        return result
    }
}