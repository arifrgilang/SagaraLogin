package com.arifrgilang.sagaralogin.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arifrgilang.sagaralogin.util.Repository

class PaymentPresenter(var ctx: Context, var mView: PaymentContract.View): PaymentContract.Presenter {
    override fun getIntentContent(intent: Intent) {
        mView.setIntentView(
            intent.getStringExtra("nama")!!,
            intent.getStringExtra("jabatan")!!,
            intent.getStringExtra("rekening")!!)
    }

    override fun getSaldo() = mView.setSaldoView(
        Repository.localDb(ctx).getInt(Repository.MONEY,0).toString())

    override fun konfirmasiTransfer(intent: Intent, nominal: String) {
        if(nominal.isEmpty() || nominal == "0"){
            mView.showToast("Nominal tidak boleh kosong!")
        } else {
            val saldo = Repository.localDb(ctx).getInt(Repository.MONEY,0)
            if (nominal.toInt() <= saldo){
                val bundle = Bundle()
                bundle.putString("nama", intent.getStringExtra("nama")!!)
                bundle.putString("jabatan", intent.getStringExtra("jabatan")!!)
                bundle.putString("nominal", nominal)
                mView.showConfirmationSheet(bundle)
            } else {
                mView.showToast("Saldo kurang!")
            }
        }
    }
}