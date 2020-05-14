package com.jayneel.eat24

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("users")

        lblreglink.setOnClickListener {
            startActivity(Intent(this,addusers::class.java))
        }

        btnlogin.setOnClickListener {
            if(txtlunm.text.toString()=="" && txtlpwd.text.toString()=="")
            {
                Toast.makeText(this,"Enter all Fileds",Toast.LENGTH_SHORT).show()
            }
            else {
                myRef.child("${txtlunm.text.toString()}")
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            val value = dataSnapshot.getValue(usermodule::class.java)
                            if (txtlpwd.text.toString() == value?.upass) {

                               var sp=getSharedPreferences("Login", Context.MODE_PRIVATE)
                                var edt: SharedPreferences.Editor=sp.edit()
                                edt.putString("Unm",txtlunm.text.toString())
                                edt.commit()

                                var int1=Intent(this@login, home::class.java)
                                int1.putExtra("unm",txtlunm.text.toString())
                                startActivity(int1)
                                finish()
                            } else
                                Toast.makeText(
                                    this@login,
                                    "Invalid usre name and password",
                                    Toast.LENGTH_SHORT
                                ).show()

                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Failed to read value
                            //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
                        }
                    })
            }
        }
    }
}

