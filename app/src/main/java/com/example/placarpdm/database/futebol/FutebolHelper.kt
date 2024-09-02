package com.example.placarpdm.database.futebol

object FutebolHelper {
    const val TIME_CASA = "timeCasa"
    const val TIME_VISITANTE = "timeVisitante"
    const val PLACAR_CASA = "placarCasa"
    const val PLACAR_VISITANTE = "placarVisitante"
    const val GANHADOR = "ganhador"
    const val TABLE_NAME = "futebol"
    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$TIME_CASA TEXT," +
            "$TIME_VISITANTE TEXT," +
            "$PLACAR_CASA INTEGER," +
            "$PLACAR_VISITANTE INTEGER," +
            "$GANHADOR TEXT)"
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}