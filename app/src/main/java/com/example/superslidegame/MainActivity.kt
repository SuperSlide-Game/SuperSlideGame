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
        var resId = imgResId

        // button onClick listener
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        imageView.setImageResource(resId)

        // accessing our relative layout from activity_main.xml
        val layout = findViewById<RelativeLayout>(R.id.layout)

        // Add ImageView to LinearLayout
        layout?.addView(imageView) // adding image to the layout
        button.setOnClickListener {
            setContentView(R.layout.initial_screen)
        }

        button2.setOnClickListener {
            setContentView(R.layout.settings_screen)
        }
    }
}
