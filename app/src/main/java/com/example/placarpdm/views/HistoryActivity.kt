package com.example.placarpdm.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placarpdm.R
import com.example.placarpdm.adapters.HistoryAdapter
import com.example.placarpdm.database.futebol.FutebolDAO
import com.example.placarpdm.databinding.ActivityHistoryBinding


class HistoryActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        val FutebolDAO = FutebolDAO(this)
        val historyList = FutebolDAO.getAll()
        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter(historyList)
        val recyclerView: RecyclerView = findViewById(R.id.rvHistory)
        adapter.notifyDataSetChanged()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}