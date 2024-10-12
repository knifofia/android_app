package knifofia.menu

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class WhipActivity : AppCompatActivity() {
    private lateinit var manager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var detector: ShakeDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_whip)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.playSound()
        this.initShakeListner()
    }

    override fun onDestroy() {
        super.onDestroy()

        detector.setOnShakeListener(null)
        manager.unregisterListener(detector)
    }

    private fun initShakeListner(){
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        detector = ShakeDetector()
        detector.setOnShakeListener {
            this.playSound()
        }
        manager.registerListener(detector, sensor, SensorManager.SENSOR_DELAY_UI)
    }

    private fun playSound() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.whip_1)
        mediaPlayer.start()
    }


}