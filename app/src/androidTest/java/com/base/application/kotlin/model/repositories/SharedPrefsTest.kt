package com.base.application.kotlin.model.repositories

import android.content.SharedPreferences
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

@RunWith(AndroidJUnit4::class)
@SmallTest
class SharedPrefsTest {

    @Mock
    private val mockSharedPreferences: SharedPreferences? = null

    @Mock
    private val mockSharedPreferencesEditor: SharedPreferences.Editor? = null

    private var sharedPrefsUnderTest: SharedPrefs? = null

    @Before
    fun setUp() {
        initMocks(this)
        sharedPrefsUnderTest = SharedPrefs(mockSharedPreferences!!)
    }

    @Test
    fun testGetString() {
        // Setup
        val expectedResult = "Test"
        `when`(mockSharedPreferences!!.getString(anyString(), anyString())).thenReturn("Test")

        // Run the test
        val result = sharedPrefsUnderTest!!.getString()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    fun testSaveString() {
        // Setup
        `when`(mockSharedPreferences!!.edit()).thenReturn(mockSharedPreferencesEditor)
        `when`(mockSharedPreferencesEditor!!.putString(anyString(), anyString())).thenReturn(
            mockSharedPreferencesEditor
        )
        `when`(mockSharedPreferencesEditor.commit()).thenReturn(true)

        // Run the test
        val result = sharedPrefsUnderTest!!.saveString("")

        // Verify the results
        assertTrue(result)

    }

    @Test
    fun testClearToDos() {
        // Setup
        `when`(mockSharedPreferences!!.edit()).thenReturn(mockSharedPreferencesEditor)
        `when`(mockSharedPreferencesEditor!!.clear()).thenReturn(mockSharedPreferencesEditor)
        `when`(mockSharedPreferencesEditor.commit()).thenReturn(true)

        // Run the test
        val result = sharedPrefsUnderTest!!.clear()

        // Verify the results
        assertTrue(result)

    }
}
