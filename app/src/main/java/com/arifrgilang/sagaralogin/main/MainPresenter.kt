package com.arifrgilang.sagaralogin.main

import android.content.Context
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.Employee
import com.arifrgilang.sagaralogin.util.Repository

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

    override fun retrieveSaldo() {
        mView.setSaldoView(Repository.localDb(ctx).getInt(Repository.MONEY, 0))
    }

    override fun addSaldo() {
        var saldo = Repository.localDb(ctx).getInt(Repository.MONEY, 0)
        if(saldo+500000<=10000000){
            saldo+=500000
            Repository.writeIntToDB(Repository.localDb(ctx), Repository.MONEY, saldo)
            mView.setSaldoView(saldo)
        } else
            mView.showToast("Saldo sudah maksimal!")
        }

    override fun minSaldo() {
        var saldo = Repository.localDb(ctx).getInt(Repository.MONEY, 0)
        if(saldo-500000>=0){
            saldo-=500000
            Repository.writeIntToDB(Repository.localDb(ctx), Repository.MONEY, saldo)
            mView.setSaldoView(saldo)
        } else
            mView.showToast("Saldo tidak bisa dikurangi lagi!")
    }
}


