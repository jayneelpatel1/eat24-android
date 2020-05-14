package com.jayneel.eat24

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_take_order.*

class takeOrder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_order)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("menu")
        val Ref = database.getReference("order")
        txttbid.text=intent.getStringExtra("tableID")
        var menudata:ArrayList<String> = arrayListOf<String>()
        var pr:Int=0



        var odata:ArrayList<orderModule> = arrayListOf<orderModule>()

        Ref.child("${txttbid.text.toString()}").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                odata.clear()
                var ad=rvlistorder(this@takeOrder,odata)

                for (v in dataSnapshot.children) {
                    var data=v.getValue(orderModule::class.java)
                    //Log.d("kk","${data.toString()}")

                    if (data != null) {
                        odata.add(data)

                    }
                }
                Log.d("KK","${ad.toString()}")
                rvorder.adapter=ad

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })
    rvorder.layoutManager= StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)



        //Auto Complete Text View Start
        myRef.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(v in dataSnapshot.children)
                {
                    var data=v.getValue(menuModule::class.java)
                    if (data != null) {
                        pr=data.mprice.toString().toInt()
                        menudata.add(data.mnm.toString())

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })
        // Auto complete Text View End
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, menudata)
        txtser.setAdapter(adapter)


        //Spiiner Logic
        var selqt:Int?=null
        qty.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selqt=1
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selqt=parent!!.selectedItem.toString().toInt()
            }

        }
        //Spinner Logic Over

        var st=0
        myRef.child("${txttbid.text.toString()}").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (v in dataSnapshot.children) {
                        st+=1
                    }

            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
            }
        })





        //Check table Status

        btnaddorder.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("order")
            val unic=myRef.push().key
//            val user=unicOrderModule("P",unic, "data",txttbid.text.toString())
//            var i:Int=1


            myRef.child("${txttbid.text.toString()}").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        st=1

                        for (v in dataSnapshot.children) {
                            st+=1

                        }

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    //Log.w(FragmentActivity.TAG, "Failed to read value.", error.toException())
                }
            })
                if(st>0) {
                    val or =
                        orderModule(txtser.text.toString(), selqt, txttbid.text.toString().toInt(),pr* selqt!!)
                    myRef.child(txttbid.text.toString()).child("$st").setValue(or)
                        .addOnCanceledListener {
                            Toast.makeText(
                                this@takeOrder,
                                "Data enter Successfully",
                                Toast.LENGTH_SHORT
                            ).show()


                        }
                }
            else {
                    val or =
                        orderModule(txtser.text.toString(), selqt, txttbid.text.toString().toInt(),pr* selqt!!)
                    myRef.child(txttbid.text.toString()).child("1").setValue(or)
                        .addOnCanceledListener {
                            Toast.makeText(
                                this@takeOrder,
                                "Data enter Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            st+=1
                        }


                }





            }// btn click end


        //btn bill generate start

        btngenbill.setOnClickListener {
            var int1=Intent(this,GenerateBill::class.java)
            int1.putExtra("tbid",txttbid.text.toString())
            startActivity(int1)
        }

        //btn bill generate End


        }


    }
