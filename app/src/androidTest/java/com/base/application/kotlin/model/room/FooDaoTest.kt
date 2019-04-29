package com.base.application.kotlin.model.room

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.utils.FooFactory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class FooDaoTest {
    private lateinit var appDatabase: AppDatabase

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun testGetFoos() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}

        //Run the test
        val retrievedFoos = appDatabase.fooDao().getFoos()

        //Verify the results
        assert(retrievedFoos.value == cachedFoos.sortedWith(compareBy({ it.id }, { it.id })))
    }

    @Test
    fun testFindById() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}

        //Run the test
        val retrievedFoos = appDatabase.fooDao().findById(1)

        //Verify the results
        assert(retrievedFoos.value?.size == 1)
        assert(retrievedFoos.value?.get(0)?.id == 1L)
    }

    @Test
    fun testCountFoos() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}

        //Run the test
        val foosCount = appDatabase.fooDao().countFoos()

        //Verify the results
        assert(foosCount.value == cachedFoos.size)
    }

    @Test
    fun testInsertAll() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)

        //Run the test
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}

        //Verify the results
        val foos = appDatabase.fooDao().getFoos()
        assert(foos.value?.isNotEmpty() == true)
    }

    @Test
    fun testUpdate() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}
        val updatedFoo = Foo(1, "Updated Title" + 1, "Updated Description" + 1)

        //Run the test
        runBlocking{appDatabase.fooDao().upDate(updatedFoo)}

        //Verify the results
        val retrievedFoos = appDatabase.fooDao().findById(1)
        assert(retrievedFoos.value?.get(0)?.equals(updatedFoo) == true)
    }

    @Test
    fun testDelete() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking { cachedFoos.forEach { appDatabase.fooDao().insertAll(it) }}
        val deletedFoo = Foo(1, "Updated Title" + 1, "Updated Description" + 1)

        //Run the test
        runBlocking{appDatabase.fooDao().delete(deletedFoo)}

        //Verify the results
        val retrievedFoos = appDatabase.fooDao().findById(1)
        assert(retrievedFoos.value?.isEmpty() == true)
    }
}