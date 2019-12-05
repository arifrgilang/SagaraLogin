package com.arifrgilang.sagaralogin.util

import android.content.Context
import android.content.SharedPreferences
import com.arifrgilang.sagaralogin.model.History
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Repository {
    companion object{
        private val db = FirebaseDatabase.getInstance()
        private lateinit var dbRef : DatabaseReference
        private var PRIVATE_MODE = 0
        private var PREF_NAME = "sagara"
        var USERID = "userid"
        var MONEY = "money"

        fun onlineDb(): DatabaseReference = db.reference

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

        fun submitHistory(nama: String, jabatan: String, nominal: String){
            val db = onlineDb()
            val hellman = Hellman()
            db.child("history")
                .push()
                .setValue(
                    History(nama, jabatan, nominal,
                        hellman.encrypt(hellman.convertEnNumber(Util.getLocalDate())),
                        hellman.encrypt(hellman.convertEnNumber(Util.getLocalTime())))
                )
        }
    }
}