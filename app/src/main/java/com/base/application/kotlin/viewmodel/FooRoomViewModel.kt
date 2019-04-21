package com.base.application.kotlin.viewmodel

import android.arch.lifecycle.ViewModel
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.repositories.FooRoomRepository
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.MutableLiveData


class FooRoomViewModel(var fooRoomRepository: FooRoomRepository)  : ViewModel(){

    private val searchId = MutableLiveData<Long>()
    var searchedFoos = Transformations.switchMap(searchId) { c -> fooRoomRepository.findFooById(c) }
    var allFoos = fooRoomRepository.getAllFoos()
    var fooCount = fooRoomRepository.countFoos()

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