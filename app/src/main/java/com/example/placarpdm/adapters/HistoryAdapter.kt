package com.example.placarpdm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.placarpdm.R
import com.example.placarpdm.models.Futebol

class HistoryAdapter(private val historyList: ArrayList<Futebol>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeTeamTv: TextView = itemView.findViewById(R.id.tvHomeHistory)
        val awayTeamTv: TextView = itemView.findViewById(R.id.tvOutHistory)
        val homeScoreTv: TextView = itemView.findViewById(R.id.tvHomePoints)
        val awayScoreTv: TextView = itemView.findViewById(R.id.tvOutPoints)
        val ivHomeTeam: ImageView = itemView.findViewById(R.id.ivHomeHistory)
        val ivAwayTeam: ImageView = itemView.findViewById(R.id.ivOutHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_row, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val futebol: Futebol = historyList.get(position)
        holder.homeTeamTv.text = futebol.timeCasa
        holder.awayTeamTv.text = futebol.timeVisitante
        holder.homeScoreTv.text = futebol.placarCasa.toString()
        holder.awayScoreTv.text = futebol.placarVisitante.toString()
        if (futebol.ganhador.equals(futebol.timeCasa)){
            holder.ivHomeTeam.visibility = View.VISIBLE
        }
        else if (futebol.ganhador.equals(futebol.timeVisitante)){
            holder.ivAwayTeam.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}