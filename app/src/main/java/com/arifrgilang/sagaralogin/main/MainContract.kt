package com.arifrgilang.sagaralogin.main

import com.arifrgilang.sagaralogin.model.Employee

interface MainContract {
    interface View{
        fun setListView(list: List<Employee>)
        fun navigateToSend()
        fun showToast(text: String)
        fun setSaldoView(saldo: Int)
    }
    interface Presenter{
        fun retrieveSaldo()
        fun getListEmployee()
        fun addSaldo()
        fun minSaldo()
    }
}