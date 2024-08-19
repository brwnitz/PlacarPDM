package com.example.placarpdm.models

import java.io.Serializable

class Basquete(val timeCasa: String, val timeVisitante: String, var tempoPartida: Int, val periodoMaximo: Int):Serializable{
    var placarCasa: Int = 0
    var placarVisitante: Int = 0
    var ganhador: String? = null
    var periodo: Int = 1
    var tempoAtual: Int = 0

    fun cestaCasa() {
        placarCasa += 2
    }

    fun cestaVisitante() {
        placarVisitante += 2
    }

    fun finalizarPeriodo() {
        if (periodo < periodoMaximo) {
            periodo++
            tempoAtual = 0
        }
    }

    fun finalizarPartida() {
        if (placarCasa > placarVisitante) {
            ganhador = timeCasa
        } else {
            ganhador = timeVisitante
        }
    }
}