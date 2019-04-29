package com.base.application.kotlin.utils

import com.base.application.kotlin.model.data.Foo

class FooFactory {
    companion object {
        fun makeCachedFooList(no: Int): ArrayList<Foo> {
            var list = ArrayList<Foo>()
            for (i in 1..no) {
                list.add(Foo(null, "Title" + 1, "Description" + 1))
            }
            return list
        }
    }
}