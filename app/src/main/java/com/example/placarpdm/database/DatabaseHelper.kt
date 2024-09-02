package com.example.placarpdm.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.placarpdm.database.futebol.FutebolHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "PlacarPDM", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(FutebolHelper.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(FutebolHelper.DROP_TABLE)
        onCreate(db)
    }
}