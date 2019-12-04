package com.arifrgilang.sagaralogin.payment

import android.content.Context
import android.content.Intent

class PaymentPresenter(var ctx: Context, var mView: PaymentContract.View): PaymentContract.Presenter {
    override fun getIntentContent(intent: Intent) {
        mView.setIntentView(
            intent.getStringExtra("nama")!!,
            intent.getStringExtra("jabatan")!!,
            intent.getStringExtra("rekening")!!)
    }
}