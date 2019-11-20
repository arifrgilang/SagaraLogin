package com.arifrgilang.sagaralogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arifrgilang.sagaralogin.util.RegisterPagerAdapter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val registerPagerAdapter =
            RegisterPagerAdapter(
                this,
                supportFragmentManager
            )
        register_view_pager.adapter = registerPagerAdapter

    }
}
