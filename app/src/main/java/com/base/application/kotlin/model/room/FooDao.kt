package com.base.application.kotlin.model.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.base.application.kotlin.model.data.Foo

/**
 * Created by Mayore on 12/04/2019.
 */

@Dao
interface FooDao {

    @get:Query("SELECT * FROM foos")
    val foos: LiveData<List<Foo>>

    @Query("SELECT * FROM foos where id LIKE  :id")
    fun findById(id: Long): LiveData<List<Foo>>

    @Query("SELECT COUNT(*) FROM foos")
    fun countFoos(): LiveData<Int>

    @Insert
    fun insertAll(vararg foos: Foo)

    @Update
    fun upDate(vararg foo: Foo)

    @Delete
    fun delete(foo: Foo)
}
