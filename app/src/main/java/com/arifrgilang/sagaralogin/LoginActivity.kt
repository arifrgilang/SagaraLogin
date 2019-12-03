package com.arifrgilang.sagaralogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
                    "arifrgilang" -> checkLogin(id, pw)
                    "ewok" -> checkLogin(id, pw)
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

    private fun checkLogin(id: String, pw: String){
        // Masukkan logic untuk Enkripsi PW
        dbRef = db.reference.child("account").child(id)
        dbRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(String::class.java)
                Log.d("onDataChange", value!!)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("OnCancelled", p0.message)
                // Masukkan logic untuk Dekripsi password
                // If password yang di dekripsi sama dengan PW
                // Maka StartActivity
            }
        })
    }
}
