package com.hamza.news.Models

/**
 * Created by hamza on 25/01/18.
 */


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JsonIp {

    @SerializedName("ip")
    @Expose
    var ip: String? = null
    @SerializedName("about")
    @Expose
    var about: String? = null
    @SerializedName("Pro!")
    @Expose
    var pro: String? = null
    @SerializedName("reject-fascism")
    @Expose
    var rejectFascism: String? = null

    override fun toString(): String {
        return "JsonIp(ip=$ip, about=$about, pro=$pro, rejectFascism=$rejectFascism)"
    }


}