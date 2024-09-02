package com.example.placarpdm.models

import java.io.Serializable

class Futebol(val timeCasa: String, val timeVisitante: String, var tempoPartida: Int): Serializable {

    var placarCasa: Int = 0
    var placarVisitante: Int = 0
    var ganhador: String?= null
    fun golCasa() {
        placarCasa++
    }

    fun golVisitante() {
        placarVisitante++
    }

    fun finalizarPartida() {
        if (placarCasa > placarVisitante) {
            ganhador = timeCasa
        } else if (placarCasa < placarVisitante) {
            ganhador = timeVisitante
        } else {
            ganhador = "Empate"
        }
    }
}