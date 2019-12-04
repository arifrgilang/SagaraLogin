package com.arifrgilang.sagaralogin.util

import android.content.Context
import android.content.SharedPreferences

class Util {
    companion object{
        private var PRIVATE_MODE = 0
        private var PREF_NAME = "sagara"
        var USERID = "userid"
        var MONEY = "money"

        fun localDb(ctx: Context): SharedPreferences =
            ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

        fun writeStringToDB(db: SharedPreferences, key: String, value: String){
            val editor = db.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun writeIntToDB(db: SharedPreferences, key: String, value: Int){
            val editor = db.edit()
            editor.putInt(key, value)
            editor.apply()
        }
    }
}