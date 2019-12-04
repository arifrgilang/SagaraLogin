package com.arifrgilang.sagaralogin.login

import android.util.Log
import com.arifrgilang.sagaralogin.util.Hellman
import com.google.firebase.database.*

class LoginPresenter(var mView: LoginContract.View): LoginContract.Presenter{
    private val db = FirebaseDatabase.getInstance()
    private lateinit var dbRef : DatabaseReference

    override fun checkLogin(id: String, pw: String) {
        if(id.isEmpty() || pw.isEmpty()){
            mView.showToast("Isi Form Login!")
        } else {
            when(id){
                "arifrgilang" -> login(id, pw)
                "dimastole" -> login(id, pw)
                "arntonius" -> login(id, pw)
                else -> {
                    mView.showToast("Username Not Found!")
                }
            }
        }
    }

    private fun login(id: String, pw: String){
        // Masukkan logic untuk Enkripsi PW
        mView.showLoading(true)
        val hellman = Hellman()
        val encryptedId = hellman.encrypt(id)
        Log.d("encypted", encryptedId)

        dbRef = db.reference.child("account").child(encryptedId)

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                val decryptedPw = hellman.decrypt(value!!)

                if(decryptedPw == pw){
                    mView.navigateToMain()
                } else{
                    mView.showToast("Password Salah")
                }
                Log.d("onDataChange", value)
                mView.showLoading(false)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("OnCancelled", p0.message)
            }
        })
    }

}