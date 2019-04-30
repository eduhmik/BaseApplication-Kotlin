package com.base.application.kotlin.model.repositories

import androidx.lifecycle.MutableLiveData
import androidx.test.runner.AndroidJUnit4
import com.base.application.kotlin.model.data.Foo
import com.base.application.kotlin.model.room.FooDao
import com.base.application.kotlin.utils.FooFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doNothing
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer

@RunWith(AndroidJUnit4::class)
class FooRoomRepositoryTest {


    @Mock
    private lateinit var mockFooDao: FooDao

    private lateinit var fooRoomRepositoryUnderTest: FooRoomRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fooRoomRepositoryUnderTest = FooRoomRepository(mockFooDao)
    }
    @Test
    fun getAllFoos() {
        // Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        Mockito.`when`(mockFooDao.getFoos()).thenReturn(MutableLiveData<List<Foo>>().apply{postValue(cachedFoos)})

        //Run the test
        val retrievedFoos = runBlocking{fooRoomRepositoryUnderTest.getAllFoos()}

        //Verify the results
        assert(retrievedFoos.value == cachedFoos.sortedWith(compareBy({ it.id }, { it.id })))
    }

    @Test
    fun findFooById() {
        // Setup
        val searchedFoos = FooFactory.makeCachedFooList(5).filter {f -> f.id == 1L}
        Mockito.`when`(mockFooDao.findById(1)).thenReturn(MutableLiveData<List<Foo>>().apply{postValue(searchedFoos)})

        //Run the test
        val retrievedFoos = runBlocking{fooRoomRepositoryUnderTest.findFooById(1)}

        //Verify the results
        assert(retrievedFoos.value?.size == 1)
        assert(retrievedFoos.value?.get(0)?.id == 1L)
    }

    @Test
    fun countFoos() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        Mockito.`when`(mockFooDao.countFoos()).thenReturn(MutableLiveData<Int>().apply{postValue(5)})

        //Run the test
        val retrievedCount = runBlocking{fooRoomRepositoryUnderTest.countFoos()}

        //Verify the results
        assert(retrievedCount.value == cachedFoos.size)
    }

    @Test(expected = Exception::class)
    fun insertFoo() {
        //Setup
        val cachedFoos = FooFactory.makeCachedFooList(5)
        runBlocking{ doThrow(Exception("")).`when`(mockFooDao).insertAll(any())}

        //Run the test
        runBlocking { cachedFoos.forEach { fooRoomRepositoryUnderTest.insertFoo(it) }}
    }

    @Test(expected = Exception::class)
    fun upDateFoo() {
        //Setup
        val updatedFoo = Foo(1, "Updated Title" + 1, "Updated Description" + 1)
        runBlocking{ doThrow(Exception("")).`when`(mockFooDao).upDate(any())}

        //Run the test
        runBlocking { fooRoomRepositoryUnderTest.upDateFoo(updatedFoo) }
    }

    @Test(expected = Exception::class)
    fun deleteFoo() {
        //Setup
        val deletedFoo = FooFactory.makeCachedFooList(5)[0]
        runBlocking{ doThrow(Exception("")).`when`(mockFooDao).delete(any())}

        //Run the test
        runBlocking { fooRoomRepositoryUnderTest.deleteFoo(deletedFoo) }
    }
}