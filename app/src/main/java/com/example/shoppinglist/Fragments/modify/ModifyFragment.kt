package com.example.shoppinglist.Fragments.modify

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoppinglist.R
import com.example.shoppinglist.model.Items
import com.example.shoppinglist.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_modify.*
import kotlinx.android.synthetic.main.fragment_modify.view.*


class




ModifyFragment : Fragment() {

    private val args by navArgs<ModifyFragmentArgs>()

    lateinit var item: Items

    private lateinit var mItemViewModel: ItemViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_modify, container, false)
        item = args.currentItem


        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        var checkmark = args.currentItem.checkmark

        view.checkBox.isChecked = checkmark == true

        view.Modify_name_ET.setText(args.currentItem.name)
        view.Modify_shop_ET.setText(args.currentItem.shop)
        view.Modify_amount_ET.setText(args.currentItem.amount.toString())
        view.Modify_price_ET.setText(args.currentItem.price.toString())


        view.checkBox.setOnClickListener{
            if (view.checkBox.isChecked)
            {
                item.checkmark = true
                Log.d("checkbox","is clicked")
            }
            else{
                item.checkmark = false
                Log.d("checkbox","unclicked")

            }

        }
        view.img_del_btn.setOnClickListener{
            delete()
        }

         view.Modify_btn.setOnClickListener{
             checkfirst()
        modifyItem()
         }

        setHasOptionsMenu(true)
        return view
    }

    private fun checkfirst(){
        if (Modify_price_ET.text.toString().isEmpty()){
            Modify_price_ET.setText("0")
        }

    }


    private fun modifyItem() {
        val name = Modify_name_ET.text.toString()
        val shop = Modify_shop_ET.text.toString()
        val amount = Integer.parseInt(Modify_amount_ET.text.toString())
        val price = Integer.parseInt(Modify_price_ET.text.toString())
        var checkmark = item.checkmark
        val image = "path"


        if (inputCheck(name)) {
            val updatedItem = Items(args.currentItem.id, name, shop, amount, price,image,checkmark)
            //modify item
            mItemViewModel.modifyItems(updatedItem)
            Toast.makeText(requireContext(), "Changes Applied", Toast.LENGTH_LONG).show()


            findNavController().navigate(R.id.action_modifyFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please enter item name", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String): Boolean{
        return !(TextUtils.isEmpty(name))
    }


    private fun delete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Delete"){_,_->
            mItemViewModel.delete(args.currentItem)
            Toast.makeText(requireContext(),"Your item has been deleted!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_modifyFragment_to_listFragment)
        }
        //Delete button confirmation
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete item?")
        builder.setMessage("Are you sure you want to delete this item from your list?")
        builder.create().show()
    }

}