package com.example.grocery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.R

class GroceryAdapter(var list:List<GroceryProducts>, val groceryproductinterface:GroceryProductClickInterface):
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {
    inner class GroceryViewHolder(productview: View):RecyclerView.ViewHolder(productview){
        val name=productview.findViewById<TextView>(R.id.ProductName)
        val Quantity=productview.findViewById<TextView>(R.id.quantity)
        val rate=productview.findViewById<TextView>(R.id.rate)
        val Amount=productview.findViewById<TextView>(R.id.amount)
        val Delete=productview.findViewById<ImageView>(R.id.delete)
    }
    interface GroceryProductClickInterface{
        fun onProductClick(groceryProducts: GroceryProducts)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.grocery_rv,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.name.text=list.get(position).Product
        holder.Quantity.text=list.get(position).Quantity.toString()
        holder.rate.text="Rs. "+list.get(position).Price.toString()
        val totalprice:Int=list.get(position).Price*list.get(position).Quantity
        holder.Amount.text="Rs. " + totalprice.toString()
        holder.Delete.setOnClickListener{
            groceryproductinterface.onProductClick(list.get(position))
        }
    }


    override fun getItemCount(): Int {
        return  list.size
    }


}
