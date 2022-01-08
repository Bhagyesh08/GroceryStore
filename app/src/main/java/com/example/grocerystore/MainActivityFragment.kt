package com.example.grocerystore

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivityFragment : Fragment(),GroceryAdapter.GroceryProductClickInterface {


    //lateinit var RV: RecyclerView
   // lateinit var FAB: FloatingActionButton
    lateinit var list: List<GroceryProducts>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var groceryViewModel: GroceryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<RecyclerView>(R.id.rv)
//        view.findViewById<FloatingActionButton>(R.id.fab)
        list = ArrayList<GroceryProducts>()
        groceryAdapter = GroceryAdapter(list, this)
        view.findViewById<RecyclerView>(R.id.rv).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.rv).adapter = groceryAdapter
        val repository = Repository(GroceryRoom(requireContext()))
        val factory = GroceryViewModelFactory(repository)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceryProducts().observe(viewLifecycleOwner, {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        })
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            openOption()
        }
    }

    fun openOption() {
        val option = Dialog(requireContext())
        option.setContentView(R.layout.grocery_option)
        val cncl = option.findViewById<Button>(R.id.cancel)
        val add = option.findViewById<Button>(R.id.add)
        val Pname = option.findViewById<EditText>(R.id.oProduct)
        val Pprice = option.findViewById<EditText>(R.id.oPrice)
        val Pquantity = option.findViewById<EditText>(R.id.oQuantity)
        cncl.setOnClickListener {
            option.dismiss()
        }
        add.setOnClickListener {
            val Prname: String = Pname.text.toString()
            val proPrice: String = Pprice.text.toString()
            val PrQuantity: String = Pquantity.text.toString()

            if (Prname.isNotEmpty() && proPrice.isNotEmpty() && PrQuantity.isNotEmpty()) {
                val pp: Int = proPrice.toInt()
                val pq: Int = PrQuantity.toInt()
                val products = GroceryProducts(Prname, pq, pp)
                groceryViewModel.insert(products)
                Toast.makeText(context, "Product Inserted!!", Toast.LENGTH_SHORT).show()
                groceryAdapter.notifyDataSetChanged()
                option.dismiss()
            } else {
                Toast.makeText(context, "Please enter all fields!!", Toast.LENGTH_SHORT).show()
                option.dismiss()
            }
        }
        option.show()
    }

    override fun onProductClick(groceryProducts: GroceryProducts) {
        groceryViewModel.delete(groceryProducts)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(context, "Item Deleted!!", Toast.LENGTH_SHORT).show()

    }
}

