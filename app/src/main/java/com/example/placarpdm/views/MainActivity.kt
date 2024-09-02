package com.example.placarpdm.views

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placarpdm.R
import com.example.placarpdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sportsArray = resources.getStringArray(R.array.sportsArray)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sportsArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sportSpinner.adapter = adapter
        binding.startButton.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            val bundle = Bundle()
            when (binding.sportSpinner.selectedItem.toString()) {
                "Futebol" -> {
                    bundle.putString("sport", "Futebol")
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                "Volei" -> {
                    bundle.putString("sport", "Volei")
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                "Basquete"->{
                    bundle.putString("sport", "Basquete")
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }
        }
        binding.btnHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}