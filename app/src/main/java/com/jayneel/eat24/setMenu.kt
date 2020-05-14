package com.jayneel.eat24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_table.*
import kotlinx.android.synthetic.main.activity_set_menu.*

class setMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_menu)
            var cat:String?=null
            spinner2.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
               // TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                cat=parent!!.selectedItem.toString()
            }

        }

        btnaddmenu.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("menu")
            val user=menuModule(txmnm.text.toString(),txprice.text.toString().toInt(),cat)
            myRef.child(txmnm.text.toString()).setValue(user).addOnCompleteListener {
                Toast.makeText(this,"Data enter Sucessfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
