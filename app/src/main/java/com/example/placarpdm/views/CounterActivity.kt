package com.example.placarpdm.views

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.placarpdm.R
import com.example.placarpdm.database.futebol.FutebolDAO
import com.example.placarpdm.databinding.ActivityCounterBinding
import com.example.placarpdm.models.Basquete
import com.example.placarpdm.models.Futebol
import com.example.placarpdm.models.HistoryManager
import com.example.placarpdm.models.Volei
import com.example.placarpdm.utils.SharedPreferencesUtil

class CounterActivity : AppCompatActivity() {

    val binding: ActivityCounterBinding by lazy {
        ActivityCounterBinding.inflate(layoutInflater)
    }

    private var paused: Boolean = false
    private var timeRemaining: Long = 0
    private var countDownTimer: CountDownTimer? = null
    private val historyManager = HistoryManager()
    var sport: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.extras
        sport = bundle?.getSerializable("sport")
        if (sport is Futebol){
            binding.houseTitle.text = (sport as Futebol).timeCasa
            binding.adversaryTitle.text = (sport as Futebol).timeVisitante
            binding.houseButton.setOnClickListener(){
                historyManager.saveState((sport as Futebol).placarCasa, (sport as Futebol).placarVisitante)
                (sport as Futebol).golCasa()
                binding.housePoints.text = (sport as Futebol).placarCasa.toString()
            }
            binding.adversaryButton.setOnClickListener(){
                historyManager.saveState((sport as Futebol).placarCasa, (sport as Futebol).placarVisitante)
                (sport as Futebol).golVisitante()
                binding.adversaryPoints.text = (sport as Futebol).placarVisitante.toString()
            }
            startTimer((sport as Futebol).tempoPartida * 60 * 1000L)
            binding.btnPause.setOnClickListener(){
                if (!paused){
                    pauseTimer()
                    paused = true
                }else{
                    resumeTimer()
                    paused = false
                }
            }
            binding.btnRewind.setOnClickListener(){
                val previousState = historyManager.undo()
                if (previousState != null){
                    (sport as Futebol).placarCasa = previousState.first
                    (sport as Futebol).placarVisitante = previousState.second
                    binding.housePoints.text = (sport as Futebol).placarCasa.toString()
                    binding.adversaryPoints.text = (sport as Futebol).placarVisitante.toString()
                }
            }
            Log.d("TIPOESPORTE", "TIPO FUTEBOL")
        }else if (sport is Basquete){
            binding.houseTitle.text = (sport as Basquete).timeCasa
            Log.d("TIPOESPORTE", "TIPO BASQUETE")
        }else if (sport is Volei){
            binding.houseTitle.text = (sport as Volei).timeCasa
            Log.d("TIPOESPORTE", "TIPO VOLEI")
        }

    }

    private fun startTimer(millisInFuture: Long){
        countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.tvCounter.text = "$minutes:$seconds"
            }

            override fun onFinish() {
                binding.tvCounter.text = "00:00"
                if (sport is Futebol){
                    (sport as Futebol).finalizarPartida()
                    FutebolDAO(this@CounterActivity).insert((sport as Futebol).timeCasa, (sport as Futebol).timeVisitante, (sport as Futebol).placarCasa, (sport as Futebol).placarVisitante, (sport as Futebol).ganhador!!)

                }
            }
        }
        countDownTimer?.start()
    }

    private fun pauseTimer(){
        countDownTimer?.cancel()
    }

    private fun resumeTimer(){
        startTimer(timeRemaining)
    }
}