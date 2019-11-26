package com.arifrgilang.sagaralogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

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
                if(username.text!!.toString().equals("arif@sagara.asia") && password.text!!.toString().equals("sagaraasia")){
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this,
                        "E-mail atau Password salah!",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
