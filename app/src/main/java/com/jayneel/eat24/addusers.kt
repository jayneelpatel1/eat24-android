package com.jayneel.eat24

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_addusers.*


class addusers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addusers)
//        spinner.setOnItemClickListener { parent, view, position, id ->
//            val nm=
//        }
        btnreg.setOnClickListener {


            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("users")
            val user=usermodule(txtrunm.text.toString(),"",txtrpwd.text.toString())
            myRef.child(txtrunm.text.toString()).setValue(user).addOnCompleteListener {
                Toast.makeText(this,"Data enter Sucessfully",Toast.LENGTH_SHORT).show()
            }

        }

    }
}
