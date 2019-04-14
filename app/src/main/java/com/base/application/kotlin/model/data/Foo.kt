package com.base.application.kotlin.model.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "foos")
data class Foo (@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name = "title") var title : String,
                @ColumnInfo(name = "description") var description : String)