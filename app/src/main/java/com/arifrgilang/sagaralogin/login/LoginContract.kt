package com.arifrgilang.sagaralogin.login

interface LoginContract {
    interface View{
        fun showToast(text: String)
        fun navigateToMain()
        fun showLoading(condition: Boolean)
    }
    interface Presenter{
        fun checkLogin(id: String, pw: String)
    }
}