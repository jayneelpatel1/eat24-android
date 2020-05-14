package com.jayneel.eat24

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashlist.view.*

class tableadapter(var ctx:Activity,var arlist:ArrayList<tablemodule>):RecyclerView.Adapter<tableadapter.tableViewholder>() {
    inner class tableViewholder(tb:View):RecyclerView.ViewHolder(tb){
        var tbid=tb.lblid
        var tbsize=tb.lblsize
        var tbst=tb.lblst
        var btn=tb.btnmanage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tableViewholder {
        var view =ctx.layoutInflater.inflate(R.layout.dashlist,parent,false)
        var vh= tableViewholder(view)
        return  vh
    }

    override fun getItemCount(): Int {
        return arlist.size
    }

    override fun onBindViewHolder(holder: tableViewholder, position: Int) {
        holder.tbid.text="Table : "+arlist[position].tbid.toString()
        holder.tbsize.text="capecity : "+arlist[position].tbsize.toString()
        holder.tbst.text=""
        holder.btn.setOnClickListener {
            var int1=Intent(ctx,takeOrder::class.java)
            int1.putExtra("tableID",arlist[position].tbid.toString())
            ctx.startActivity(int1)


        }

    }

}

