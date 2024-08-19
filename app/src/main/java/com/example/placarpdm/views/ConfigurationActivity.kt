package com.example.placarpdm.views

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placarpdm.R
import com.example.placarpdm.databinding.ActivityConfigurationBinding
import com.example.placarpdm.models.Basquete
import com.example.placarpdm.models.Futebol
import com.example.placarpdm.models.Volei

class ConfigurationActivity : AppCompatActivity() {

    val binding: ActivityConfigurationBinding by lazy {
        ActivityConfigurationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val bundle = intent.extras
        val sport = bundle?.getString("sport")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        when (sport) {
            "Futebol" -> {
                binding.llFutebol.visibility = android.view.View.VISIBLE
                binding.startCounter.setOnClickListener {
                    if (binding.etHouseFutebol.text.toString().isNotEmpty() && binding.etOutsideFutebol.text.toString().isNotEmpty() && binding.etTempoFutebol.text.toString().isNotEmpty()) {
                        val futebol: Futebol = Futebol(
                            binding.etHouseFutebol.text.toString(),
                            binding.etOutsideFutebol.text.toString(),
                            binding.etTempoFutebol.text.toString().toInt()
                        )
                        Log.d("Futebol", futebol.timeCasa)
                        Log.d("Futebol", futebol.timeVisitante)
                        Log.d("Futebol", futebol.tempoPartida.toString())
                        val intent = android.content.Intent(this, CounterActivity::class.java)
                        val bundle = android.os.Bundle()
                        bundle.putSerializable("sport", futebol)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    else
                    {
                        android.widget.Toast.makeText(this, "Preencha todos os campos", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }
            "Volei" -> {
                binding.llVolei.visibility = android.view.View.VISIBLE
                binding.startCounter.setOnClickListener {
                    if (binding.etHouseVolei.text.toString()
                            .isNotEmpty() && binding.etOutsideVolei.text.toString()
                            .isNotEmpty() && binding.etSets.text.toString()
                            .isNotEmpty() && binding.etPontTieBreak.text.toString().isNotEmpty()
                    ) {
                        val volei: Volei = Volei(
                            binding.etHouseVolei.text.toString(),
                            binding.etOutsideVolei.text.toString(),
                            binding.etSets.text.toString().toInt(),
                            binding.etPontTieBreak.text.toString().toInt(),
                            binding.etPontMaxima.text.toString().toInt()
                        )
                        val intent = android.content.Intent(this, CounterActivity::class.java)
                        val bundle = android.os.Bundle()
                        bundle.putSerializable("sport", volei)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    else
                    {
                        android.widget.Toast.makeText(this, "Preencha todos os campos", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }
            "Basquete" -> {
                binding.llBasquete.visibility = android.view.View.VISIBLE
                binding.startCounter.setOnClickListener {
                    if (binding.etHouseBasquete.text.toString().isNotEmpty() && binding.etOutsideBasquete.text.toString().isNotEmpty() && binding.etTempoBasquete.text.toString().isNotEmpty()) {
                        val basquete: Basquete = Basquete(
                            binding.etHouseBasquete.text.toString(),
                            binding.etOutsideBasquete.text.toString(),
                            binding.etTempoBasquete.text.toString().toInt(),
                            binding.etPeriodos.text.toString().toInt()
                        )
                        val intent = android.content.Intent(this, CounterActivity::class.java)
                        val bundle = android.os.Bundle()
                        bundle.putSerializable("sport", basquete)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                    else
                    {
                        android.widget.Toast.makeText(this, "Preencha todos os campos", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}