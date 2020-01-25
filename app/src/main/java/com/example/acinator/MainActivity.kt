package com.example.acinator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var BaseUrl = "https://api.audd.io/"
    var API_TOKEN = "35961dda32ab78e0a043d0c6ba6b0976"
    var nameOfSong : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameOfSong = findViewById<TextView>(R.id.nameOfSong)

    }
    fun textError (){//видає повідомлення про невведений рядок з пісні
        val error = Toast.makeText(this,"Enter line", Toast.LENGTH_SHORT)
        error.show()
    }
    fun DoneSecondPage (view: View){//функція для кнопки Done щоб перейти на некст сторінку
        if(editText.text.toString() !=""){// перевірка на введення рядка пісні
        val doneButton = Intent(this,SecondActivity::class.java)
        

        startActivity(doneButton)
        }else textError()
    }
    fun name_Of_Song(view: View){
        if(editText.text.toString() !=""){


            GetSong(editText.text.toString())
        }else textError()
    }

    fun GetSong(req : String){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Song_Service::class.java)
        val call = service.getSong(req,API_TOKEN)
        var result: String=" "

        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.code()==200){

                    val song_response = response.body()!!

                    val stringBuilder =
                        song_response.result!![0].artist + "-"+
                                song_response.result!![0].title
                    //data = stringBuilder
                    result = stringBuilder
                    nameOfSong!!.text = result
                    //**Присвоєння значення поля відбувається безпосередньо у функціі

                }
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {result = "Have a trouble!!!"}
        })

    }

}
