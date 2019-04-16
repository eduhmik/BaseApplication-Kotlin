package com.base.application.kotlin.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FooResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var foos: List<Foo>? = null

}