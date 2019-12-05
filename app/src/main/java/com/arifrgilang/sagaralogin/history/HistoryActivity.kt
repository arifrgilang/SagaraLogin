package com.arifrgilang.sagaralogin.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.History
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryContract.View {

    private lateinit var mPresenter: HistoryContract.Presenter
    private lateinit var mAdapter: FirebaseRecyclerAdapter<History, HistoryRvAdapter.MyViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        mPresenter = HistoryPresenter(this,this )
        initRv()
        mPresenter.retrieveHistory()
    }

    private fun initRv() {
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        manager.stackFromEnd = true

        rv_history.layoutManager = manager
    }

    override fun setRvAdapter(adapter: FirebaseRecyclerAdapter<History, HistoryRvAdapter.MyViewHolder>) {
        mAdapter = adapter
        rv_history.adapter = adapter
        mAdapter.startListening()
    }
}
