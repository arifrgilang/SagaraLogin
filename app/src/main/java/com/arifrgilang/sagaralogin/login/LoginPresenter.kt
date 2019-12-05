package com.arifrgilang.sagaralogin.login

import android.content.Context
import android.util.Log
import com.arifrgilang.sagaralogin.util.Hellman
import com.arifrgilang.sagaralogin.util.Repository
import com.google.firebase.database.*

class LoginPresenter(var ctx: Context, var mView: LoginContract.View): LoginContract.Presenter{

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

        val dbOnline = Repository.onlineDb()
            .child("account").child(encryptedId)

        dbOnline.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                val decryptedPw = hellman.decrypt(value!!)

                if(decryptedPw == pw){
                    Repository.writeStringToDB(Repository.localDb(ctx), Repository.USERID, id)
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