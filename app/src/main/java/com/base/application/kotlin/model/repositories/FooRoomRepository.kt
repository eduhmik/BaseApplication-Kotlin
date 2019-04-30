package com.base.application.kotlin.model.repositories

import androidx.lifecycle.LiveData
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.room.FooDao
import kotlinx.coroutines.*

class FooRoomRepository(var fooDao : FooDao) {

    suspend fun getAllFoos(): LiveData<List<Foo>>{
        return withContext(Dispatchers.Default) { fooDao.getFoos() }
    }

    suspend fun findFooById(id: Long): LiveData<List<Foo>>{
        return withContext(Dispatchers.Default) { fooDao.findById(id) }
    }

    suspend fun countFoos(): LiveData<Int>{
        return withContext(Dispatchers.Default) { fooDao.countFoos() }
    }

    fun insertFoo(foo: Foo): Job {
        return GlobalScope.launch  { fooDao.insertAll(foo) }
    }

    fun upDateFoo(foo: Foo): Job {
        return GlobalScope.launch  { fooDao.upDate(foo) }
    }

    fun deleteFoo(foo: Foo): Job {
        return GlobalScope.launch  { fooDao.delete(foo) }
    }
}