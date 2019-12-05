package com.arifrgilang.sagaralogin.util

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object{
        fun getLocalDate() : String{
            val time = Calendar.getInstance().time
            val format = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
            return format.format(time)
        }

        fun getLocalTime(): String{
            val time = Calendar.getInstance().time
            val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            return format.format(time)
        }
    }
}