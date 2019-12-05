package com.arifrgilang.sagaralogin.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arifrgilang.sagaralogin.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity(), PaymentContract.View {
    private lateinit var mPresenter: PaymentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        mPresenter = PaymentPresenter(this, this)
        mPresenter.getIntentContent(intent)
        mPresenter.getSaldo()

        pay_button.setOnClickListener {
            mPresenter.konfirmasiTransfer(intent, nominal_gaji.text.toString())
        }
    }

    override fun setIntentView(n: String, j: String, r: String) {
        p_nama.text = n
        p_jabatan.text = j
        p_rekening.text = r
    }

    override fun setSaldoView(nominal: String) { saldo_bos.text = nominal }
    override fun showConfirmationSheet(bundle: Bundle) {
        val fragment = PwFragment()
        fragment.arguments = bundle
        fragment.show(supportFragmentManager, PwFragment().tag)
    }

    override fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}
