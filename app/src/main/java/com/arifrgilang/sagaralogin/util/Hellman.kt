package com.arifrgilang.sagaralogin.util

import android.annotation.SuppressLint

class Hellman {
    // Private Key
    private var privateKey: MutableList<Int> = mutableListOf(1,2,5,11,32,87,141)
    private var a: Int = 200 // a
    private var p: Int = 307 // prime number
    // Public key
    private var publicKey: MutableList<Int> = mutableListOf()
    private var aInverse = modInverse(a,p)

    init{ generateKey() }

    @SuppressLint("DefaultLocale")
    fun encrypt(plaintText: String): String{
        val ptCharArray = plaintText.toUpperCase().toCharArray()
        val bitArray = mutableListOf<String>()
        val resultArray = mutableListOf<String>()
        // Convert char to binary
        for(c in ptCharArray) bitArray.add(c.toInt().toString(2))

        for(bitLetter in bitArray){
            var j = 0
            var numberValue = 0
            for(item in bitLetter){
                if(item == '1') numberValue+=publicKey[j]
                j++
            }
            resultArray.add(numberValue.toString())
        }
        return resultArray.joinToString("-")
    }

    @SuppressLint("DefaultLocale")
    fun decrypt(cipherText: String): String{
        val decryptedArray = mutableListOf<String>()
        val arrayofCT = cipherText.split("-")
        for(letter in arrayofCT){
            // z = a(-1)*y mod p
            val result = aInverse.times(letter.toInt()).rem(p)
            var bitResult = ""
            var pt = 0
            for(i in 6 downTo 0){
                val temp = privateKey[i]
                if((pt+temp)<=result){
                    pt+=temp
                    bitResult+="1"
                } else{
                    bitResult+="0"
                }
            }
            decryptedArray.add(binaryToString(bitResult.reversed()))
        }
        return decryptedArray.joinToString("").toLowerCase()
    }

    private fun binaryToString(binary: String): String{
        val chars = CharArray(binary.length / 7)
        var i = 0

        while (i < binary.length) {
            val str = binary.substring(i, i + 7)
            val nb = Integer.parseInt(str, 2)
            chars[i / 7] = nb.toChar()
            i += 7
        }

        return String(chars)
    }

    private fun generateKey() {
        // ti = a*s(i) mod p
        for (i in 0 until privateKey.size) publicKey.add(privateKey[i].times(a).rem(p))
    }

    private fun modInverse(a: Int, p: Int): Int {
        val a = a%p
        for (x in 1 until p) if ((a*x) % p == 1) return x
        return 1
    }
}