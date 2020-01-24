package com.example.acinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun textError (){//видає повідомлення про невведений рядок з пісні
        val error = Toast.makeText(this,"Enter line", Toast.LENGTH_SHORT)
        error.show()
    }
    fun DoneSecondPage (view: View){//функція для кнопки Done щоб перейти на некст сторінку
        if(editText.text.toString() !=""){// перевірка на введення рядка пісні
        val doneButton = Intent(this,SecondActivity::class.java)
        val nameOfSong = editText.text.toString()
        doneButton.putExtra(SecondActivity.TOTAL_STRING, nameOfSong)
        startActivity(doneButton)
        }else textError()
    }

}
