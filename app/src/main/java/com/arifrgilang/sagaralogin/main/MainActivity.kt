package com.arifrgilang.sagaralogin.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.Employee
import com.arifrgilang.sagaralogin.util.EmployeeRvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mAdapter: EmployeeRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this, this)
        mAdapter = EmployeeRvAdapter(this)

        employee_rv.layoutManager = LinearLayoutManager(this)
        employee_rv.adapter = mAdapter

        mPresenter.getListEmployee()
    }

    override fun setListView(list: List<Employee>) = mAdapter.setList(list)

    override fun navigateToSend() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
