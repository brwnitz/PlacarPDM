package com.example.placarpdm.models

import java.util.Stack

class HistoryManager {
    private val historyStack: Stack<Pair<Int, Int>> = Stack()

    fun saveState(homeScore: Int, awayScore: Int) {
        historyStack.push(Pair(homeScore, awayScore))
    }

    fun undo(): Pair<Int, Int>? {
        return if (historyStack.isNotEmpty()) {
            historyStack.pop()
        } else {
            null
        }
    }
}