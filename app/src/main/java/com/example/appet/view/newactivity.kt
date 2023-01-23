package com.example.appet.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.appet.R
import com.example.appet.uitel.getProgessDrawable
import com.example.appet.uitel.loadImage
import org.w3c.dom.Text


class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newactivity)

        /**get Data*/
        val animalIntent = intent
        val animalName = animalIntent.getStringExtra("name")
        val animalInfo = animalIntent.getStringExtra("info")
        val animalImg = animalIntent.getStringExtra("img")

        var name = findViewById<TextView>(R.id.name)
        var info = findViewById<TextView>(R.id.info)
        var img = findViewById<ImageView>(R.id.img)
        /**call text and images*/
        name.text = animalName
        info.text = animalInfo
        img.loadImage(animalImg, getProgessDrawable(this))
    }
    /**ok now run it */
}