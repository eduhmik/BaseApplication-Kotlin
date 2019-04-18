package com.base.application.kotlin.model.repositories

import android.arch.lifecycle.MutableLiveData
import android.provider.Contacts
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.rest.FooRestApi
import com.base.application.kotlin.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FooRestRepository(var fooRestApi: FooRestApi) {

    fun getFooList(): MutableLiveData<Resource<List<Foo>>> {
        val result = MutableLiveData<Resource<List<Foo>>>()
        result.setValue(Resource.loading(null))
        GlobalScope.launch {
            try {
                val postListResponse = fooRestApi.getFooList().awaitResult().getOrThrow()
                withContext(Contacts.Intents.UI) { result.value = Resource.success(postListResponse.data) }
            }
            catch (e: Exception) {
                withContext(Contacts.Intents.UI) { result.value = Resource.error("Unable to get post list", null) }
            }
        }
        return result
    }

}


