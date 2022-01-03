package com.example.grocery

import android.app.Dialog
import android.app.VoiceInteractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),GroceryAdapter.GroceryProductClickInterface {
    lateinit var RV:RecyclerView
    lateinit var FAB:FloatingActionButton
    lateinit var list:List<GroceryProducts>
    lateinit var groceryAdapter:GroceryAdapter
    lateinit var groceryViewModel:GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RV=findViewById(R.id.rv)
        FAB=findViewById(R.id.fab)
        list=ArrayList<GroceryProducts>()
        groceryAdapter= GroceryAdapter(list,this)
        RV.layoutManager=LinearLayoutManager(this)
        RV.adapter=groceryAdapter
        val repository=Repository(GroceryRoom(this))
        val factory=GroceryViewModelFactory(repository)
        groceryViewModel=ViewModelProvider(this,factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceryProducts().observe(this,{
            groceryAdapter.list=it
            groceryAdapter.notifyDataSetChanged()
        })
        FAB.setOnClickListener{
            openOption()
        }
    }
    fun openOption(){
        val option=Dialog(this)
        option.setContentView(R.layout.grocery_option)
        val cncl=option.findViewById<Button>(R.id.cancel)
        val add=option.findViewById<Button>(R.id.add)
        val Pname=option.findViewById<EditText>(R.id.oProduct)
        val Pprice=option.findViewById<EditText>(R.id.oPrice)
        val Pquantity=option.findViewById<EditText>(R.id.oQuantity)
        cncl.setOnClickListener{
            option.dismiss()
        }
        add.setOnClickListener {
            val Prname:String=Pname.text.toString()
            val proPrice:String=Pprice.text.toString()
            val PrQuantity:String=Pquantity.text.toString()

            if(Prname.isNotEmpty()&&proPrice.isNotEmpty()&&PrQuantity.isNotEmpty()){
                val pp:Int=proPrice.toInt()
                val pq:Int=PrQuantity.toInt()
                val products=GroceryProducts(Prname,pq,pp)
                groceryViewModel.insert(products)
                Toast.makeText(applicationContext,"Product Inserted!!",Toast.LENGTH_SHORT).show()
                groceryAdapter.notifyDataSetChanged()
                option.dismiss()
            }


            else{
                Toast.makeText(applicationContext,"Please enter all fields!!",Toast.LENGTH_SHORT).show()
                option.dismiss()
            }
        }
        option.show()
    }

    override fun onProductClick(groceryProducts: GroceryProducts) {
        groceryViewModel.delete(groceryProducts)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted!!",Toast.LENGTH_SHORT).show()

    }
}