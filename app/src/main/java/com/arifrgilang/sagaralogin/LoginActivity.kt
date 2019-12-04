package com.arifrgilang.sagaralogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arifrgilang.sagaralogin.cryptosystem.Hellman
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val db = FirebaseDatabase.getInstance()
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        choose_login_button.setOnClickListener {
            if(username.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
                Toast.makeText(this,
                    "Isi form login!",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                val id = username.text!!.toString()
                val pw = password.text!!.toString()

                when(id){
                    "arifrgilang" -> checkLogin(this, id, pw)
                    "dimastole" -> checkLogin(this, id, pw)
                    "arntonius" -> checkLogin(this, id, pw)
                    else -> {
                        Toast.makeText(this,
                            "Username not found!",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun checkLogin(ctx: Context, id: String, pw: String){
        // Masukkan logic untuk Enkripsi PW
        val hellman = Hellman()
        val encryptedId = hellman.encrypt(id)
        Log.d("encypted", encryptedId)

        dbRef = db.reference.child("account").child(encryptedId)

        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                val decryptedPw = hellman.decrypt(value!!)
                if(decryptedPw == pw){
                    startActivity(Intent(ctx, MainActivity::class.java))
                    finish()
                } else{
                    Toast.makeText(ctx,
                        "Password Salah!",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                Log.d("onDataChange", value)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("OnCancelled", p0.message)
            }
        })
    }
}
