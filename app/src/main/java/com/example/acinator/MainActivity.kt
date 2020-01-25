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

    fun GetSong(req : String):String{
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Song_Service::class.java)
        val call = service.getSong(req,API_TOKEN)
        var result: String=" "

        call.enqueue(object : Callback<Result>{
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if(response.code()==200){

                    val song_response = response.body()!!

                    val stringBuilder =
                        song_response.result!![0].artist + " "+
                                song_response.result!![0].title
                    //data = stringBuilder
                    result = stringBuilder

                    //**Присвоєння значення поля відбувається безпосередньо у функціі

                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {result = "Have a trouble!!!"}


        })
        return result
    }

}
