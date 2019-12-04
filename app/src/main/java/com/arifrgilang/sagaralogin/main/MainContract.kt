package com.arifrgilang.sagaralogin.main

import com.arifrgilang.sagaralogin.model.Employee

interface MainContract {
    interface View{
        fun setListView(list: List<Employee>)
        fun navigateToSend()
    }
    interface Presenter{
        fun getListEmployee()
        fun addSaldo()
        fun minSaldo()
    }
}