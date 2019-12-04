package com.arifrgilang.sagaralogin.payment

import android.content.Intent

interface PaymentContract {
    interface View{
        fun setIntentView(n: String, j: String, r: String)
    }
    interface Presenter{
        fun getIntentContent(intent: Intent)
    }
}