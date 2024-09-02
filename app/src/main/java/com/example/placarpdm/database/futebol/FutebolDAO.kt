package com.example.placarpdm.database.futebol

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.placarpdm.database.DatabaseHelper
import com.example.placarpdm.models.Futebol

class FutebolDAO(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun insert(timeCasa: String, timeVisitante: String, placarCasa: Int, placarVisitante: Int, ganhador: String): Long {
        val values = ContentValues().apply {
            put(FutebolHelper.TIME_CASA, timeCasa)
            put(FutebolHelper.TIME_VISITANTE, timeVisitante)
            put(FutebolHelper.PLACAR_CASA, placarCasa)
            put(FutebolHelper.PLACAR_VISITANTE, placarVisitante)
            put(FutebolHelper.GANHADOR, ganhador)
        }
        return db.insert(FutebolHelper.TABLE_NAME, null, values)
    }

    fun update(id: Long, timeCasa: String, timeVisitante: String, placarCasa: Int, placarVisitante: Int, ganhador: String): Int {
        val values = ContentValues().apply {
            put(FutebolHelper.TIME_CASA, timeCasa)
            put(FutebolHelper.TIME_VISITANTE, timeVisitante)
            put(FutebolHelper.PLACAR_CASA, placarCasa)
            put(FutebolHelper.PLACAR_VISITANTE, placarVisitante)
            put(FutebolHelper.GANHADOR, ganhador)
        }
        return db.update(FutebolHelper.TABLE_NAME, values, "id = ?", arrayOf(id.toString()))
    }

    fun delete(id: Long): Int {
        return db.delete(FutebolHelper.TABLE_NAME, "id = ?", arrayOf(id.toString()))
    }

    @Suppress("Range")
    fun getAll(): ArrayList<Futebol> {
        val query = "SELECT * FROM ${FutebolHelper.TABLE_NAME}"
        val cursor = db.rawQuery(query, null)
        val list = ArrayList<Futebol>()
        if (cursor.moveToFirst()){
            do {
                val timeCasa = cursor.getString(cursor.getColumnIndex(FutebolHelper.TIME_CASA))
                val timeVisitante = cursor.getString(cursor.getColumnIndex(FutebolHelper.TIME_VISITANTE))
                val placarCasa = cursor.getInt(cursor.getColumnIndex(FutebolHelper.PLACAR_CASA))
                val placarVisitante = cursor.getInt(cursor.getColumnIndex(FutebolHelper.PLACAR_VISITANTE))
                val ganhador = cursor.getString(cursor.getColumnIndex(FutebolHelper.GANHADOR))
                val futebol: Futebol = Futebol(timeCasa, timeVisitante, 0)
                futebol.ganhador = ganhador
                futebol.placarCasa = placarCasa
                futebol.placarVisitante = placarVisitante
                list.add(futebol)
            } while (cursor.moveToNext())
            }
        return list
    }

    fun getById(id: Long): Cursor {
        return db.query(FutebolHelper.TABLE_NAME, null, "id = ?", arrayOf(id.toString()), null, null, null)
    }
}