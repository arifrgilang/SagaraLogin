package com.arifrgilang.sagaralogin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.arifrgilang.sagaralogin.LoginActivity
import com.arifrgilang.sagaralogin.R
import kotlinx.android.synthetic.main.fragment_page_one.*

class PageOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_Login.setOnClickListener {
            startActivity(Intent(activity!!, LoginActivity::class.java))
            activity!!.finish()
        }
        next_register.setOnClickListener {
            if(email_register.text.isNullOrEmpty() || password_register.text.isNullOrEmpty()){
                Toast.makeText(activity,
                    "Isi form register",
                    Toast.LENGTH_SHORT)
                    .show()
            } else {
                if(email_register.text!!.toString().contains("@")){
//                    Toast.makeText(activity,
//                        "Bener",
//                        Toast.LENGTH_SHORT)
//                        .show()
                    val viewPager = activity!!.findViewById<ViewPager>(R.id.register_view_pager)
                    viewPager.currentItem = 2
                } else {
                    Toast.makeText(activity,
                        "Tulis format E-mail dengan benar!",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}
