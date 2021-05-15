package com.applab.app_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.Date

// 撰寫與資料庫建立,升級,銷毀與CRUD相關程序
class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // SingleTon (companion object相當於 Java 的 static inner class 靜態內部類別)
    companion object {
        val DATABASE_VERSION = 1 // 資料庫版本
        val DATABASE_NAME = "MyDB.db" // 資料庫名稱

        // 建立資料表 Student 的 SQL 語句
        val SQL_CREATE_STUDENT = "" +
                "CREATE TABLE Student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score INTEGER," +
                "ct BIGINT)"
        // 刪除資料表 Student 的 SQL 語句
        val SQL_DELETE_STUDENT = "DROP TABLE IF EXISTS Student"

    }

    // 預設資料表的建立
    override fun onCreate(db: SQLiteDatabase?) {
        // db 指的是 MyDB.db 資料庫容器
        db?.execSQL(SQL_CREATE_STUDENT)
    }

    // 資料庫升級
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_STUDENT)
        onCreate(db)
    }

    // 新增資料
    fun createStudent(name: String, score: Int) {
        // 取得資料庫
        val db = writableDatabase
        // 準備要新增的紀錄
        val values = ContentValues()
        values.put("name", name)
        values.put("score", score)
        values.put("ct", Date().time)
        // 新增到資料庫
        // nullColumnHack 是指若欄位是空白要放的內容為何 ?
        // action 異動筆數(此次影響資料表的紀錄數量)
        val action = db.insert("Student", null, values)
        Log.d("DB", "createStudent: action=" + action)

    }
}