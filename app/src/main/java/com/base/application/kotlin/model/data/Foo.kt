package com.base.application.kotlin.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.text.TextUtils

@Entity(tableName = "foos")
data class Foo (@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name = "title") var title : String,
                @ColumnInfo(name = "description") var description : String){

    fun equals(foo: Foo) = when {
        TextUtils.equals(title, foo.title) -> false
        TextUtils.equals(description, foo.description) -> false
        else -> true
    }
}