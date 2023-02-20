package com.example.superslidegame

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = ImageView(this)
        // setting height and width of imageview
        imageView.layoutParams= LinearLayout.LayoutParams(screenWidth / 2, screenWidth / 2)
        imageView.x= screenWidth / 4f // setting margin from left
        imageView.y= screenHeight/5f // setting margin from top

        // accessing our custom image which we added in drawable folder
        val imgResId = R.drawable.main_logo
        val resId = imgResId

        // button onClick listener
        val playButton = findViewById<Button>(R.id.play_button)
        val settingsButton = findViewById<Button>(R.id.settings_button)
        imageView.setImageResource(resId)

        // accessing our relative layout from activity_main.xml
        val layout = findViewById<RelativeLayout>(R.id.layout)

        // Add ImageView to LinearLayout
        layout?.addView(imageView) // adding image to the layout

        playButton.setOnClickListener {
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }

        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsScreen::class.java)
            startActivity(intent)
        }
    }
}
