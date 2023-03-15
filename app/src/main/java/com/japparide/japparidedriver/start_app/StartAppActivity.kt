package com.japparide.japparidedriver.start_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.japparide.japparidedriver.R
import com.japparide.japparidedriver.ui.auth.AuthenticationActivity
import com.japparide.japparidedriver.ui.maps.MapsActivity

class StartAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_app)


        supportActionBar?.hide()


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



        Handler().postDelayed({

            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            finish()



        },2000

        )
    }
}