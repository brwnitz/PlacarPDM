package com.example.placarpdm.models

import java.io.Serializable

class Volei(val timeCasa: String, val timeVisitante: String, val setMaximo: Int, val pontTieBreak: Int, var pontMaxima: Int): Serializable {
    var placarCasa: Int = 0
    var placarVisitante: Int = 0
    var setAtual: Int = 1
    var setCasa: Int = 0
    var setVisitante: Int = 0
    var ganhador: String? = null
    fun pontoCasa() {
        placarCasa++
    }

    fun pontoVisitante() {
        placarVisitante++
    }

    fun finalizarSet() {
        if (placarCasa > placarVisitante) {
            setCasa++
        } else {
            setVisitante++
        }
        if (setAtual != setMaximo){
            setAtual++
            placarCasa = 0
            placarVisitante = 0
        }
        if (setAtual == setMaximo-1){
            pontMaxima = pontTieBreak
        }
    }

    fun finalizarJogo(){
        if (setCasa > setVisitante){
            ganhador = timeCasa
        } else {
            ganhador = timeVisitante
        }
    }
}