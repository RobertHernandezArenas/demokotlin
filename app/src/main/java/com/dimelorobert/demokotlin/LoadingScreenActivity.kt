package com.dimelorobert.demokotlin

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoadingScreenActivity : AppCompatActivity() {
    private var isDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mantener la Splash Screen visible hasta que los datos se carguen
        splashScreen.setKeepOnScreenCondition { !isDataLoaded }

        // Simular la carga de datos
        loadData()

        showLoadingScreen()
    }

    private fun loadData() {
        // Simular una carga de datos
        Handler(Looper.getMainLooper()).postDelayed({
            isDataLoaded = true
        }, 1450) // 3 segundos de retraso
    }

    private fun showLoadingScreen(){

        object : CountDownTimer(6000,1000 ) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()
    }
}