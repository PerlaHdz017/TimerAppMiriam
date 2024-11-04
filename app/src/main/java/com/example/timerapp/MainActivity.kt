package com.example.timerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var timeInput: EditText
    private lateinit var startButton: Button
    private var countdownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)
        timeInput = findViewById(R.id.timeInput)
        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener { startTimer() }
    }

    private fun startTimer() {
        val input = timeInput.text.toString()
        val durationInSeconds = input.toLongOrNull() ?: return
        val durationInMillis = durationInSeconds * 1000

        countdownTimer?.cancel() // Cancela el temporizador anterior si existe
        timerText.text = formatTime(durationInMillis)

        countdownTimer = object : CountDownTimer(durationInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = formatTime(millisUntilFinished)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerText.text = "¡Tiempo finalizado!"
            }
        }.start()
    }

    @SuppressLint("DefaultLocale")
    private fun formatTime(millis: Long): String {
        val seconds = (millis / 1000) % 60
        val minutes = (millis / 1000) / 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
