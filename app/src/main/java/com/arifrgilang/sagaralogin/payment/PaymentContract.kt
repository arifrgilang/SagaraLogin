package com.arifrgilang.sagaralogin.payment

import android.content.Intent
import android.os.Bundle

interface PaymentContract {
    interface View{
        fun setIntentView(n: String, j: String, r: String)
        fun setSaldoView(nominal: String)
        fun showConfirmationSheet(bundle: Bundle)
        fun showToast(text: String)
    }
    interface Presenter{
        fun getIntentContent(intent: Intent)
        fun getSaldo()
        fun konfirmasiTransfer(intent: Intent, nominal: String)
    }
}