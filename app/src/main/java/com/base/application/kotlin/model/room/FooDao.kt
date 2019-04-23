package com.base.application.kotlin.model.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.base.application.kotlin.model.data.Foo

/**
 * Created by Mayore on 12/04/2019.
 */

@Dao
interface FooDao {

    @Query("SELECT * FROM foos")
    suspend fun getFoos(): LiveData<List<Foo>>

    @Query("SELECT * FROM foos where id LIKE  :id")
    suspend fun findById(id: Long): LiveData<List<Foo>>

    @Query("SELECT COUNT(*) FROM foos")
    suspend fun countFoos(): LiveData<Int>

    @Insert
    suspend fun insertAll(vararg foos: Foo)

    @Update
    suspend fun upDate(vararg foo: Foo)

    @Delete
    suspend fun delete(foo: Foo)
}
