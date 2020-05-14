package com.jayneel.eat24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_table.*

class AddTable : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_table)

        btnaddtable.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("table")
            val user=tablemodule(lblfdpr.text.toString(),txtsize.text.toString(),"A")
            myRef.child(lblfdpr.text.toString()).setValue(user).addOnCompleteListener {
                Toast.makeText(this,"Data enter Sucessfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,home::class.java))
                finish()
            }
        }
    }
}
