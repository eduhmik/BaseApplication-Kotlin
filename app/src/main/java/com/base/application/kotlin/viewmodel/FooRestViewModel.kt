package com.base.application.kotlin.viewmodel

import android.arch.lifecycle.ViewModel
import com.base.application.kotlin.model.repositories.FooRestRepository

class FooRestViewModel (var fooRestRepository: FooRestRepository) : ViewModel(){

    var fetchedFoos = fooRestRepository.getFooList()

}