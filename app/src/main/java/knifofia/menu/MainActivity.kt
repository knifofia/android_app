package knifofia.menu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rollButton = findViewById<Button>(R.id.rollButton)
        val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)

        rollButton.setOnClickListener {
            val rand = Random.nextInt(seekBar.progress)+1
            resultsTextView.text = rand.toString()
        }

        this.initWhipActivityButton()
    }

    private fun initWhipActivityButton() {
        val btnWhipActivity = findViewById<Button>(R.id.btnWhipActivity)
        btnWhipActivity.setOnClickListener {
            val intent = Intent(this, WhipActivity::class.java)
            startActivity(intent)
        }
    }
}