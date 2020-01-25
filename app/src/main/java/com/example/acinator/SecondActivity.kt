package com.example.acinator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    companion object{
        var TOTAL_STRING = "our_string"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nameOfSong = intent.getStringExtra(TOTAL_STRING)
        nameSong.text = nameOfSong
        setContentView(R.layout.activity_second)
    }
}
