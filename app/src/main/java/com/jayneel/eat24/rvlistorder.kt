package com.jayneel.eat24

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dashlist.view.*
import kotlinx.android.synthetic.main.listoforder.view.*

class rvlistorder(var ctx:Activity,var arlist:ArrayList<orderModule>):RecyclerView.Adapter<rvlistorder.orderlistViewholder>() {
    inner class orderlistViewholder(tb: View) : RecyclerView.ViewHolder(tb) {
        var tbnm = tb.lblclitmnm
        var tbsize = tb.lblclistqty
        var tbst = tb.lbllistprice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderlistViewholder {
        var view = ctx.layoutInflater.inflate(R.layout.listoforder, parent, false)
        var vh = orderlistViewholder(view)
        return vh
    }

    override fun getItemCount(): Int {
        return arlist.size
    }

    override fun onBindViewHolder(holder: orderlistViewholder, position: Int) {
        holder.tbnm.text = "Iteam:" + arlist[position].mnn.toString()
        holder.tbsize.text = "Qty :" + arlist[position].qty.toString()
        holder.tbst.text = "price : "+ arlist[position].price.toString()

    }
}