package com.arifrgilang.sagaralogin.fragments


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.arifrgilang.sagaralogin.MainActivity
import com.arifrgilang.sagaralogin.R
import kotlinx.android.synthetic.main.fragment_page_two.*
import java.text.SimpleDateFormat
import java.util.*

class PageTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myCalendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)

            val format = "MM/dd/yy"
            val sdf = SimpleDateFormat(format, Locale.US)
            register_tanggal_lahir.setText(sdf.format(myCalendar.time))
        }

        register_tanggal_lahir.setOnClickListener {
            DatePickerDialog(activity!!,
                date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show()
        }

        val adapterKotaJabar = ArrayAdapter.createFromResource(activity!!,
            R.array.jabar_list, android.R.layout.simple_spinner_item)
        val adapterKotaJateng = ArrayAdapter.createFromResource(activity!!,
            R.array.jateng_list, android.R.layout.simple_spinner_item)
        val adapterProvinsi = ArrayAdapter.createFromResource(activity!!,
            R.array.provinsi_list, android.R.layout.simple_spinner_item)

        adapterKotaJabar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterKotaJateng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_provinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(spinner_provinsi.selectedItem.equals("Jawa Barat")){
                    spinner_kota.adapter = adapterKotaJabar
                } else {
                    spinner_kota.adapter = adapterKotaJateng
                }
            }
        }
        spinner_kota.adapter = adapterKotaJabar
        spinner_provinsi.adapter = adapterProvinsi

        // Form Checker
        finish_register.setOnClickListener {
            if(register_nama.text.isNullOrEmpty()
                || register_alamat.text.isNullOrEmpty()
                || register_tanggal_lahir.text.isNullOrEmpty()
                || register_kodepos.text.isNullOrEmpty()
                || spinner_kota.selectedItem.toString().equals("(pilih kota)")
                || spinner_provinsi.selectedItem.toString().equals("(pilih provinsi)")){
                Toast.makeText(activity!!,
                    "Isi Form dengan lengkap!",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                startActivity(Intent(activity, MainActivity::class.java))
            }
        }
    }
}
