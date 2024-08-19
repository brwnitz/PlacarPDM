package com.example.placarpdm.views

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placarpdm.R
import com.example.placarpdm.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    val binding: ActivityCounterBinding by lazy {
        ActivityCounterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_counter)
        val housePointsElement: TextView = findViewById(R.id.housePoints)
        val housePointsPlus: Button = findViewById(R.id.houseButton)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        housePointsPlus.setOnClickListener {
            var currentHousePoints = housePointsElement.text.toString().toInt()
            currentHousePoints += 1
            housePointsElement.setText(currentHousePoints.toString())
        }
        val adversaryPointsElement: TextView = findViewById(R.id.adversaryPoints)
        val adversaryPointsPlus: Button = findViewById(R.id.adversaryButton)
        adversaryPointsPlus.setOnClickListener {
            var currentAdversaryPoints = adversaryPointsElement.text.toString().toInt()
            currentAdversaryPoints += 1
            adversaryPointsElement.setText(currentAdversaryPoints.toString())
        }
    }
}