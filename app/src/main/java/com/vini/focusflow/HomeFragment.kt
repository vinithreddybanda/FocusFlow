package com.vini.focusflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    private lateinit var stopwatchText: TextView
    private lateinit var startStopButton: Button
    private lateinit var timer: CountDownTimer
    private var timeElapsed: Long = 0L
    private var isRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        stopwatchText = view.findViewById(R.id.stopwatch_text)
        startStopButton = view.findViewById(R.id.start_stop_button)

        startStopButton.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        return view
    }

    private fun startTimer() {
        timeElapsed = 0L
        isRunning = true
        timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeElapsed += 1000
                updateStopwatchText()
            }

            override fun onFinish() {
                // Handle onFinish if needed
            }
        }.start()
        startStopButton.text = "Stop"
    }

    @SuppressLint("SetTextI18n")
    private fun pauseTimer() {
        timer.cancel()
        isRunning = false
        startStopButton.text = "Start"
    }

    private fun updateStopwatchText() {
        val minutes = timeElapsed / 60000
        val seconds = (timeElapsed % 60000) / 1000
        val milliseconds = timeElapsed % 1000

        stopwatchText.text = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds)
    }
}