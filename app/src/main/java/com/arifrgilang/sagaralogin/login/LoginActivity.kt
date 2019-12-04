package com.arifrgilang.sagaralogin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arifrgilang.sagaralogin.main.MainActivity
import com.arifrgilang.sagaralogin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var mPresenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this, this)
        choose_login_button.setOnClickListener {
            mPresenter.checkLogin(username.text.toString(), password.text.toString())
        }
    }

    override fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    override fun navigateToMain() =
        startActivity(Intent(this, MainActivity::class.java))

    override fun showLoading(condition: Boolean) {
        choose_login_button.text = if (condition) "Please Wait. . ." else "Login"
        choose_login_button.isEnabled = !condition
    }
}
