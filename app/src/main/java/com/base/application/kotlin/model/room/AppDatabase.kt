package com.base.application.kotlin.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.base.application.kotlin.model.data.Foo

@Database(entities = [Foo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fooDao(): FooDao
}