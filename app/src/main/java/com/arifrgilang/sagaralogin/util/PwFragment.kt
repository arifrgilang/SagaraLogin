package com.arifrgilang.sagaralogin.util

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.arifrgilang.sagaralogin.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_pw.*

class PwFragment : BottomSheetDialogFragment() {

    private lateinit var nama: String
    private lateinit var jabatan: String
    private lateinit var nominal: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        nama = arguments!!.getString("nama")!!
        jabatan = arguments!!.getString("jabatan")!!
        nominal = arguments!!.getString("nominal")!!

        return inflater.inflate(R.layout.fragment_pw, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = activity!!.applicationContext

        val userID = Repository.localDb(ctx)
            .getString(Repository.USERID,"")
        val hellman = Hellman()
        val encryptedId = hellman.encrypt(userID!!)

        confirm_button.setOnClickListener {
            val pw = pw_confirmation.text.toString()
            val dbOnline = Repository.onlineDb().child("account").child(encryptedId)
            // logic for check password
            dbOnline.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val value = p0.getValue(String::class.java)
                    val decryptedPw = hellman.decrypt(value!!)
                    if(decryptedPw == pw){
                        // Write history to online DB
                        Repository.submitHistory(nama, jabatan, nominal)
                        Toast.makeText(ctx, "Transfer Berhasil", Toast.LENGTH_SHORT).show()
                        activity!!.finish()
                    } else{
                        Toast.makeText(ctx, "Password Salah", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(p0: DatabaseError) { Log.d("OnCancelled", p0.message) }
            })
        }
    }
}
