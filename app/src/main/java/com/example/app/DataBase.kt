package com.example.app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBase(val context: Context , val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "DataBaseUser", factory , 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT , email TEXT , password TEXT)"
        p0!!.execSQL(query)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(p0)

    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("password", user.password)

        val p0 = this.writableDatabase
        p0.insert("users", null , values)

        p0.close()


    }

    fun getUser(login: String, password: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery(
            "SELECT * FROM users WHERE login = '$login' AND password = '$password' ",
            null
        )
        return result.moveToFirst()
    }
}