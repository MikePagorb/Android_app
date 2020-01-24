package com.example.acinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun DoneSecondPage (view: View){//функція для кнопки Done щоб перейти на некст сторінку
        if(editText.text.toString() !=""||editText.text.toString() != null){// перевірка на введення рядка пісні
        val doneButton = Intent(this,SecondActivity::class.java)
        val nameOfSong = editText.text.toString()
        doneButton.putExtra(SecondActivity.TOTAL_STRING, nameOfSong)
        startActivity(doneButton)
        }
    }
}
