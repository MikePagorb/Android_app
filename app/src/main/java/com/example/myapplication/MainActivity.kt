package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.myapplication.Result
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var BaseUrl="https://api.audd.io/"
    var API_TOKEN = "35961dda32ab78e0a043d0c6ba6b0976"
    var data : String=" "
    private var text : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text =findViewById(R.id.textView)
        //Виклик функції
        GetSong("I'm waking up to ash and dust")

    }

    internal fun GetSong(req : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Song_Service::class.java)
        val call = service.getSong(req,API_TOKEN)
        call.enqueue(object : Callback<Result>{
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.code()==200){

                    val song_response = response.body()!!

                    val stringBuilder =
                           song_response.result!![0].artist + " "+
                                   song_response.result!![0].title
                    //data = stringBuilder

                    //**Присвоєння значення поля відбувається безпосередньо у функціі
                    text!!.text= stringBuilder


                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                 //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}
