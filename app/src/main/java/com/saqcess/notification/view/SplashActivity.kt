package com.saqcess.notification.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.saqcess.notification.R
import com.saqcess.notification.viewmodel.TokenUpdateViewModel

class SplashActivity : AppCompatActivity() {
    private lateinit var fireBasetoken: String
    private val SPLASH_TIME_MS: Long = 2000
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(findViewById(R.id.toolbar))

        sendToken();
        handler();
    }

    private fun sendToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.i("getInstanceId failed::", task.exception.toString())
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                fireBasetoken = task.result!!.token

                // Log and toast
                // String msg = getString(R.string.msg_token_fmt, token);
                Log.i("token::", fireBasetoken)

                val tokenUpdateViewModel:TokenUpdateViewModel= TokenUpdateViewModel();
                tokenUpdateViewModel.uploadToken(applicationContext,fireBasetoken);
                //   Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            })
    }

    private fun handler() {
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } catch (e: Exception) {
                }
            }
        }
        thread.start()
    }
}