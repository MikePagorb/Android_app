package com.example.myapplication

import com.google.gson.annotations.SerializedName


class Songs{
    @SerializedName("song_id")
    var song_id : String? = null
    @SerializedName("artist_id")
    var artist_id : String? = null
    @SerializedName("title")
    var title : String? = null
    @SerializedName("title_with_featured")
    var title_with_featured : String? = null
    @SerializedName("full_title")
    var full_title : String? = null
    @SerializedName("artist")
    var artist : String? = null
    @SerializedName("lyrics")
    var lyrics : String? = null
}
class Result {


    @SerializedName("status")
    var status: String? = null
    @SerializedName("result")
    var result  = ArrayList<Songs>()
}
