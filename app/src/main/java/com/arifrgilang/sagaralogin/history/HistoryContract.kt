package com.arifrgilang.sagaralogin.history

import com.arifrgilang.sagaralogin.model.History
import com.firebase.ui.database.FirebaseRecyclerAdapter

interface HistoryContract {
    interface View{
        fun setRvAdapter(adapter: FirebaseRecyclerAdapter<History, HistoryRvAdapter.MyViewHolder>)
    }
    interface Presenter{
        fun retrieveHistory()
    }
}