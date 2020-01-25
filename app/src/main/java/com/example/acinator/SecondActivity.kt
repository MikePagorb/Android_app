package com.example.acinator

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message

import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object{
        var EXTRA_MESSAGE = "our_string"
    }
    var playBtn: Button? = null
    var positionBar: SeekBar? = null
    var volumeBar: SeekBar? = null
    var elapsedTimeLabel: TextView? = null
    var remainingTimeLabel: TextView? = null
    var mp: MediaPlayer? = null
    var totalTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        playBtn = findViewById<View>(R.id.playBtn) as Button
        elapsedTimeLabel = findViewById<View>(R.id.elapsedTimeLabel) as TextView
        remainingTimeLabel = findViewById<View>(R.id.remainingTimeLabel) as TextView
        // Media Player
        mp = MediaPlayer.create(this, R.raw.eminem)
        mp!!.isLooping = true
        mp!!.seekTo(0)
        mp!!.setVolume(0.5f, 0.5f)
        totalTime = mp!!.duration
        // Position Bar
        positionBar = findViewById<View>(R.id.positionBar) as SeekBar
        positionBar!!.max = totalTime
        positionBar!!.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp!!.seekTo(progress)
                        positionBar!!.progress = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            }
        )
        // Volume Bar
        volumeBar = findViewById<View>(R.id.volumeBar) as SeekBar
        volumeBar!!.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val volumeNum = progress / 100f
                    mp!!.setVolume(volumeNum, volumeNum)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            }
        )
        // Thread (Update positionBar & timeLabel)
        Thread(Runnable {
            while (mp != null) {
                try {
                    val msg = Message()
                    msg.what = mp!!.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val currentPosition = msg.what
            // Update positionBar.
            positionBar!!.progress = currentPosition
            // Update Labels.
            val elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel!!.text = elapsedTime
            val remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel!!.text = "- $remainingTime"
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel: String? = ""
        val min = time / 1000 / 60
        val sec = time / 1000 % 60
        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec
        return timeLabel
    }

    fun playBtnClick(view: View?) {
        if (!mp!!.isPlaying) { // Stopping
            mp!!.start()
            playBtn!!.setBackgroundResource(R.drawable.stop)
        } else { // Playing
            mp!!.pause()
            playBtn!!.setBackgroundResource(R.drawable.play)
        }
    }

}
