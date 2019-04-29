package com.base.application.kotlin.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.base.application.kotlin.model.data.Foo

@Database(entities = [Foo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fooDao(): FooDao
}