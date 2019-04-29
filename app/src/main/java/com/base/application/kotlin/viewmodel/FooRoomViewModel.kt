package com.base.application.kotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.repositories.FooRoomRepository
import androidx.lifecycle.Transformations
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class FooRoomViewModel(var fooRoomRepository: FooRoomRepository)  : ViewModel(){

    private val searchId = MutableLiveData<Long>()
    var searchedFoos = Transformations.switchMap(searchId) { c -> runBlocking{fooRoomRepository.findFooById(c) }}
    var allFoos = runBlocking{ fooRoomRepository.getAllFoos()}
    var fooCount = runBlocking{fooRoomRepository.countFoos()}

    fun setSearchId(id : Long){
        searchId.value = id
    }

    fun insertFoo(foo: Foo){
        fooRoomRepository.insertFoo(foo)
    }

    fun upDateFoo(foo: Foo){
        fooRoomRepository.upDateFoo(foo)
    }

    fun deleteFoo(foo: Foo){
        fooRoomRepository.deleteFoo(foo)
    }

}