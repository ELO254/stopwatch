package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var minutes = 0
    private var hours = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            hours = savedInstanceState.getInt("hours")
            minutes = savedInstanceState.getInt("minutes")
            seconds = savedInstanceState.getInt("seconds")
        }

        var start :Button = findViewById(R.id.btnstart)
        var stop : Button = findViewById(R.id.btnstop)
        var reset : Button = findViewById(R.id.btnreset)

                                                                    

        start.setOnClickListener {
            runtimer()
        }
        stop.setOnClickListener {
            if (savedInstanceState != null) {
                savedInstanceState.putInt("hours",hours)
                savedInstanceState.putInt("minutes",minutes)
                savedInstanceState.putInt("seconds",seconds)
            }



        }
        reset.setOnClickListener {
            hours = 0
            minutes = 0
            seconds = 0

        }


    }

    private fun runtimer() {
        var stopwatch : TextView = findViewById(R.id.textView)
        var handler = Handler()
        handler.post(object : Runnable{
            override fun run() {
                if(seconds == 60){
                    seconds = 0
                    minutes = minutes + 1

                }else if (minutes == 60){
                    minutes = 0
                    hours = hours + 1


                }
                var time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,seconds)
                stopwatch.text = time
                seconds++

                handler.postDelayed(this,1000)

            }

        })
    }

}