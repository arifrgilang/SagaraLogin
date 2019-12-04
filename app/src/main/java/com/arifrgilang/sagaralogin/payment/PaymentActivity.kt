package com.arifrgilang.sagaralogin.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arifrgilang.sagaralogin.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity(), PaymentContract.View {
    private lateinit var mPresenter: PaymentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        mPresenter = PaymentPresenter(this, this)
        mPresenter.getIntentContent(intent)
    }

    override fun setIntentView(n: String, j: String, r: String) {
        p_name.text = n
        p_jabatan.text = j
        p_rekening.text = r
    }
}
