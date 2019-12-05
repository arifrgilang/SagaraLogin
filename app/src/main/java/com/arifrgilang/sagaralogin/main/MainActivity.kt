package com.arifrgilang.sagaralogin.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.history.HistoryActivity
import com.arifrgilang.sagaralogin.model.Employee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mAdapter: EmployeeRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this, this)
        mPresenter.retrieveSaldo()

        initRv()

        down_button.setOnClickListener{ mPresenter.minSaldo() }
        up_button.setOnClickListener { mPresenter.addSaldo() }
        history_button.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun initRv(){
        mAdapter = EmployeeRvAdapter(this)
        employee_rv.layoutManager = LinearLayoutManager(this)
        employee_rv.adapter = mAdapter

        mPresenter.getListEmployee()
    }

    override fun setListView(list: List<Employee>) = mAdapter.setList(list)

    override fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    override fun onResume() {
        super.onResume()
        mPresenter.retrieveSaldo()
    }
    override fun setSaldoView(saldo: Int) { saldo_text.text = saldo.toString() }
}
