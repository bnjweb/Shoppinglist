package com.example.shoppinglist.Fragments.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.shoppinglist.R
import kotlinx.android.synthetic.main.fragment_item.view.*


class ItemFragment : Fragment() {

    private val args by navArgs<ItemFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        view.Item_name_TV.text = args.item.name
        view.Item_shop_TV.text = args.item.shop
        view.Item_amount_TV.text = args.item.amount.toString()
        view.Item_price_TV.text = args.item.price.toString()


        return view
    }

}