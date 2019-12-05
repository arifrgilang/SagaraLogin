package com.arifrgilang.sagaralogin.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.History
import com.arifrgilang.sagaralogin.util.Hellman
import com.arifrgilang.sagaralogin.util.Repository
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.history_viewholder.view.*

class HistoryRvAdapter(val ctx: Context, option: FirebaseRecyclerOptions<History>):
    FirebaseRecyclerAdapter<History, HistoryRvAdapter.MyViewHolder>(option) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(ctx)
            .inflate(R.layout.history_viewholder, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, item: History) {
        val randomId = getRef(position).key.toString()
        Repository.onlineDb().child("history").child(randomId)
            .addValueEventListener(object: ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) { holder.bind(item) }
                override fun onCancelled(p0: DatabaseError) { Log.d("OnCancelled", p0.message) }
            })
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(h: History){
            val hellman = Hellman()
            Log.d("nominal decrypt", hellman.convertDeNumber(hellman.decrypt(h.nominal!!)))
            itemView.h_nominal.text = hellman.convertDeNumber(hellman.decrypt(h.nominal!!))
            itemView.h_nama.text = hellman.decryptWithSpace(hellman.decrypt(h.nama!!))
            itemView.h_jabatan.text = hellman.decryptWithSpace(hellman.decrypt(h.jabatan!!))

            val date = hellman.convertDeNumber(hellman.decryptNumWithSpace(hellman.decrypt(h.date!!)))
            val time = hellman.convertDeNumber(hellman.decryptNumWithSpace(hellman.decrypt(h.time!!)))
            val a = date.split(" ").joinToString("-")
            val b = time.split(" ").joinToString(":")

            itemView.h_datetime.text = b + ", " + a
        }
    }
}