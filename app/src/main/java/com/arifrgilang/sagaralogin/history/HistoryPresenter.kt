package com.arifrgilang.sagaralogin.history

import android.content.Context
import com.arifrgilang.sagaralogin.model.History
import com.arifrgilang.sagaralogin.util.Repository
import com.firebase.ui.database.FirebaseRecyclerOptions

class HistoryPresenter(var ctx: Context, var mView: HistoryContract.View)
    : HistoryContract.Presenter {
    override fun retrieveHistory() {
        val db = Repository.onlineDb().child("history")
        val option = FirebaseRecyclerOptions.Builder<History>()
            .setQuery(db, History::class.java)
            .build()
        val adapter =
            HistoryRvAdapter(ctx, option)
        mView.setRvAdapter(adapter)
    }
}