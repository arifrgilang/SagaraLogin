package com.arifrgilang.sagaralogin.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arifrgilang.sagaralogin.R
import com.arifrgilang.sagaralogin.model.Employee
import kotlinx.android.synthetic.main.employee_viewholder.view.*

class EmployeeRvAdapter: RecyclerView.Adapter<EmployeeRvAdapter.EmpViewHolder>() {

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
        holder.bind(list[position])

    class EmpViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(e: Employee){
            itemView.vh_nama.text = e.nama
            itemView.vh_jabatan.text = e.jabatan
            itemView.vh_rekening.text = e.rekening
        }
    }
}