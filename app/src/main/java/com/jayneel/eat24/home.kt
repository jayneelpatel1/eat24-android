package com.jayneel.eat24

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("table")

        var sp=getSharedPreferences("Login", Context.MODE_PRIVATE)
        fullname.text=sp.getString("Unm","user")

        var tabledata:ArrayList<tablemodule> = arrayListOf<tablemodule>()

        myRef.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                tabledata.clear()
                var ad=tableadapter(this@home,tabledata)
                for(v in dataSnapshot.children)
                {
                    var data=v.getValue(tablemodule::class.java)
                    if (data != null) {
                        tabledata.add(data)

                    }
                }
                rv.adapter=ad
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })

        rv.layoutManager=StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Add_table -> {
                startActivity(Intent(this, AddTable::class.java))
                true
            }
            R.id.action_set_menu -> {
                startActivity(Intent(this, setMenu::class.java))
                true
            }
            R.id.action_logout -> {
//                var sp=getSharedPreferences("Login", Context.MODE_PRIVATE)
//                var edt: SharedPreferences.Editor=sp.edit()
//                edt.putString("Unm","")
//                edt.commit()
                startActivity(Intent(this, login::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}
