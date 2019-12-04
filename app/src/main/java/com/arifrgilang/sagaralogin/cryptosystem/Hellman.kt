package com.arifrgilang.sagaralogin.cryptosystem

import android.util.Log
import java.nio.charset.Charset


class Hellman {
    private val UTF8: Charset = Charset.forName("UTF-8")
    // superincreasing big integer array
    var privateKey: MutableList<Int> = mutableListOf(1,2,5,11,32,87,141)
    // Public key from `ti = a*si mod p`
    lateinit var publicKey: MutableList<Int>
    var a: Int = 200 // a
    var p: Int = 307 // prime number
    var euclidean = 241

    init{ generateKey() }

    private fun generateKey() {
        publicKey = mutableListOf()

        val range = privateKey.size -1
        // ti = a*s(i) mod p
        for (i in 0..range) publicKey.add(privateKey[i].times(a).rem(p))
    }

    fun encrypt(plaintText: String): String{
        val ptCharArray = plaintText.toUpperCase().toCharArray()
        val bitArray = mutableListOf<String>()
        val resultArray = mutableListOf<String>()
        for(c in ptCharArray){
            bitArray.add(Integer.toString(c.toInt(),2))
        }
        for(letter in bitArray){
            var j = 0
            var numberValue = 0
            for(item in letter){
                if(item == '1'){
                    numberValue+=publicKey[j]
                }
                j++
            }
            resultArray.add(numberValue.toString())
        }
        Log.d("Encrypted Result", resultArray.reversed().joinToString("-"))
//        return result.toString()
        return resultArray.reversed().joinToString("-")
    }

}