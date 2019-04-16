package com.base.application.kotlin.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class BaseResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

}
