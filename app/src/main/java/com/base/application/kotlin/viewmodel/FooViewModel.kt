package com.base.application.kotlin.viewmodel

import android.arch.lifecycle.ViewModel
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.repositories.FooRepository
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.MutableLiveData


class FooViewModel(var fooRepository: FooRepository)  : ViewModel(){

    private val searchId = MutableLiveData<Long>()
    var searchedFoos = Transformations.switchMap(searchId) { c -> fooRepository.findFooById(c) }
    var allFoos = fooRepository.getAllFoos()
    var fooCount = fooRepository.countFoos()

    fun setSearchId(id : Long){
        searchId.value = id
    }

    fun insertFoo(foo: Foo){
        fooRepository.insertFoo(foo)
    }

    fun upDateFoo(foo: Foo){
        fooRepository.upDateFoo(foo)
    }

    fun deleteFoo(foo: Foo){
        fooRepository.deleteFoo(foo)
    }

}