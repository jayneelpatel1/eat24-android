package com.jayneel.eat24

import android.content.Intent
import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_generate_bill.*
import kotlinx.android.synthetic.main.activity_take_order.*
import java.time.LocalDate
import java.util.*

class GenerateBill : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_bill)


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("PastData")
        val Ref = database.getReference("order")
        var totprice=0
        lbltbnobill.text=intent.getStringExtra("tbid")
        Ref.child("${lbltbnobill.text.toString()}").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for (v in dataSnapshot.children) {
                    var data=v.getValue(orderModule::class.java)
                    //Log.d("kk","${data.toString()}")

                    if (data != null) {
                        totprice=totprice+data.price.toString().toInt()
                    }
                }
                lbltamount.text=totprice.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })
        btnsave.setOnClickListener {
            val kry=myRef.push().key
            if (kry != null) {
                val dat=LocalDate.now()
                val oldtr=oldtrmodule(kry,dat.toString(),totprice)
                myRef.child(kry).setValue(oldtr).addOnCompleteListener {
                    Ref.child(lbltbnobill.text.toString()).removeValue()
                    Toast.makeText(this,"Bill Grnerated",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,home::class.java))
                    finish()
                }
            }
        }


    }
}
