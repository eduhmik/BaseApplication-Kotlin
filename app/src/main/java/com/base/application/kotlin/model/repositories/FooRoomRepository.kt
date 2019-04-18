package com.base.application.kotlin.model.repositories

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.room.FooDao

class FooRoomRepository(var fooDao : FooDao) {

    fun getAllFoos(): LiveData<List<Foo>>{
        return fooDao.foos
    }

    fun findFooById(id: Long): LiveData<List<Foo>>{
        return fooDao.findById(id)
    }

    fun countFoos(): LiveData<Int>{
        return fooDao.countFoos()
    }

    fun insertFoo(foo: Foo){
        val insertFooAsyncTask = InsertFooAsyncTask(fooDao).execute(foo)
    }

    fun upDateFoo(foo: Foo){
        val updateFooAsyncTask = UpdateFooAsyncTask(fooDao).execute(foo)
    }

    fun deleteFoo(foo: Foo){
        val deleteFooAsyncTask = DeleteFooAsyncTask(fooDao).execute(foo)
    }



    private class InsertFooAsyncTask(var fooDao : FooDao) : AsyncTask<Foo, Unit, Unit>() {
        override fun doInBackground(vararg p0: Foo?) {
            fooDao.insertAll(p0[0]!!)
        }
    }


    private class DeleteFooAsyncTask(var fooDao : FooDao) : AsyncTask<Foo, Unit, Unit>() {

        override fun doInBackground(vararg p0: Foo?) {
            fooDao.delete(p0[0]!!)
        }
    }


    private class UpdateFooAsyncTask(var fooDao : FooDao) : AsyncTask<Foo, Unit, Unit>() {

        override fun doInBackground(vararg p0: Foo?) {
            fooDao.upDate(p0[0]!!)
        }
    }
}