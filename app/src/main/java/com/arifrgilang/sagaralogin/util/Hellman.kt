package com.arifrgilang.sagaralogin.util

import android.annotation.SuppressLint
import android.util.Log

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

    fun convertEnNumber(numbers: String): String{
        val numberArray = numbers.toCharArray()
        val charArray = mutableListOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J')
        val result = mutableListOf<Char>()
        for (c in numberArray){
            when(c){
                '0' -> result.add(charArray[0])
                '1' -> result.add(charArray[1])
                '2' -> result.add(charArray[2])
                '3' -> result.add(charArray[3])
                '4' -> result.add(charArray[4])
                '5' -> result.add(charArray[5])
                '6' -> result.add(charArray[6])
                '7' -> result.add(charArray[7])
                '8' -> result.add(charArray[8])
                '9' -> result.add(charArray[9])
                ' ' -> result.add(' ')
            }
        }
        Log.d("tostr", result.joinToString(""))
        return result.joinToString("")
    }

    fun convertDeNumber(chars: String): String{
        val charArray = chars.toCharArray()
        val numberArray = mutableListOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        val result = mutableListOf<Char>()
        for (c in charArray){
            when(c){
                'a' -> result.add(numberArray[0])
                'b' -> result.add(numberArray[1])
                'c' -> result.add(numberArray[2])
                'd' -> result.add(numberArray[3])
                'e' -> result.add(numberArray[4])
                'f' -> result.add(numberArray[5])
                'g' -> result.add(numberArray[6])
                'h' -> result.add(numberArray[7])
                'i' -> result.add(numberArray[8])
                'j' -> result.add(numberArray[9])
                ' ' -> result.add(' ')
            }
        }
        Log.d("toNumber", result.joinToString(""))
        return result.joinToString("")
    }

    @SuppressLint("DefaultLocale")
    fun decryptWithSpace(text: String): String {
        val arr = text.split("@")
        val output = mutableListOf<String>()
        for (word in arr) output.add(word.capitalize())
        return output.joinToString(" ")
    }

    @SuppressLint("DefaultLocale")
    fun decryptNumWithSpace(text: String): String {
        val arr = text.split("@")
        val output = mutableListOf<String>()
        for (word in arr) output.add(word)
        return output.joinToString(" ")
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