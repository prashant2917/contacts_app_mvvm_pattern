package com.pocket.kumbhashree.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.pocket.kumbhashree.databinding.ActivitySplashBinding
import com.pocket.kumbhashree.extensions.startNewActivity
import com.pocket.kumbhashree.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler.postDelayed(runnable, SCREEN_TIMEOUT)
    }

    companion object {
        const val SCREEN_TIMEOUT = 3 * 1000L
    }

    private val runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startNewActivity(intent, true)

    }
}