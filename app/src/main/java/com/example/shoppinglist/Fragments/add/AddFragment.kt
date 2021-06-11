package com.example.shoppinglist.Fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.example.shoppinglist.viewModel.ItemViewModel
import com.example.shoppinglist.model.Items
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        view.Add_btn.setOnClickListener{
            checkfirst()
            insertIntoDatabase()
        }
        return view
    }

    private fun checkfirst(){
        if (Price_ET.text.toString().isEmpty()){
            Price_ET.setText("0")
        }
    }

    private fun inputCheck(name:String): Boolean{
        return !(TextUtils.isEmpty(name))
    }


    private fun insertIntoDatabase() {
        val name = Name_ET.text.toString()
        val shop = shop_ET.text.toString()
        val image = "path"
        val amount = Amount_ET.text
        val price = Price_ET.text
        val checkmark = false
        if(inputCheck(name)){
        val items = Items(
            0,
            name,
            shop,
            Integer.parseInt(amount.toString()),
            Integer.parseInt(price.toString()),
                image,
            checkmark
        )

            mItemViewModel.addItem(items)
            Toast.makeText(requireContext(),"Added to Shopping list!",Toast.LENGTH_LONG).show()
            //return to the listview
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        //error message
        else{
            Toast.makeText(requireContext(),"Please fill out item name!",Toast.LENGTH_LONG).show()

        }
        }
}