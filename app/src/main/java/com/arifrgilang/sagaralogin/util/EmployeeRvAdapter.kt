package com.arifrgilang.sagaralogin.util

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.Employee
import com.arifrgilang.sagaralogin.payment.PaymentActivity
import kotlinx.android.synthetic.main.employee_viewholder.view.*

class EmployeeRvAdapter(var ctx: Context): RecyclerView.Adapter<EmployeeRvAdapter.EmpViewHolder>() {

    private var list: MutableList<Employee> = mutableListOf()

    fun setList(employees: List<Employee>){
        list.addAll(employees)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_viewholder, parent, false)
        return EmpViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) =
        holder.bind(list[position], ctx)


    class EmpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(e: Employee, c: Context){
            itemView.vh_nama.text = e.nama
            itemView.vh_jabatan.text = e.jabatan
            itemView.vh_rekening.text = e.rekening

            itemView.card_holder.setOnClickListener{
                val intent = Intent(c, PaymentActivity::class.java)
                intent.putExtra("nama", e.nama)
                intent.putExtra("jabatan", e.jabatan)
                intent.putExtra("rekening", e.rekening)
                c.startActivity(intent)
            }
        }

    }
}