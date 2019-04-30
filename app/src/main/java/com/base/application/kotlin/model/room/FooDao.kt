package com.base.application.kotlin.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.base.application.kotlin.model.data.Foo

/**
 * Created by Mayore on 12/04/2019.
 */

@Dao
interface FooDao {

    //TODO:  Using suspend with @query

    @Query("SELECT * FROM foos")
    fun getFoos(): LiveData<List<Foo>>

    @Query("SELECT * FROM foos where id LIKE  :id")
    fun findById(id: Long): LiveData<List<Foo>>

    @Query("SELECT COUNT(*) FROM foos")
    fun countFoos(): LiveData<Int>

    @Insert
    suspend fun insertAll(vararg foos: Foo)

    @Update
    suspend fun upDate(vararg foo: Foo)

    @Delete
    suspend fun delete(foo: Foo)
}
