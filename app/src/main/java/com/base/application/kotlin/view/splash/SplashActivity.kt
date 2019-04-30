package com.base.application.kotlin.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.base.application.kotlin.R
import com.base.application.kotlin.view.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        Timer().schedule(timerTask { startActivity(Intent(this@SplashActivity, MainActivity::class.java)); finish() }, 3000)

    }
}
