package com.arifrgilang.sagaralogin.main

import android.content.Context
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.Employee

class MainPresenter(private val ctx: Context, private val mView: MainContract.View): MainContract.Presenter {
    private var listNama: MutableList<String> = mutableListOf()
    private var listJabatan: MutableList<String> = mutableListOf()
    private var listRekening: MutableList<String> = mutableListOf()
    private var listEmployee: MutableList<Employee> = mutableListOf()

    override fun getListEmployee() {
        listNama.addAll(ctx.resources.getStringArray(R.array.list_nama))
        listJabatan.addAll(ctx.resources.getStringArray(R.array.list_jabatan))
        listRekening.addAll(ctx.resources.getStringArray(R.array.list_rekening))

        for(i in 0 until listNama.size)
            listEmployee.add(Employee(listNama[i], listJabatan[i], listRekening[i]))

        mView.setListView(listEmployee)
    }

    override fun addSaldo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minSaldo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}