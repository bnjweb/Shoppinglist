package com.example.shoppinglist.Fragments.list

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.model.Items
import com.example.shoppinglist.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel


private var list= listOf<Items>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        mItemViewModel.readAllData.observe(viewLifecycleOwner, Observer { items ->
            adapter.setData(items)
            list = items
        })


        view.add_img_btn.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        view.img_share_btn.setOnClickListener{
            shareList()
        }

        setHasOptionsMenu(true)
        return view
    }


    private fun shareList(){
        var resStr=""

        for (items in list){
            resStr = resStr + "Item:" +" " + items.name +
                    "\n" + "Shop:" + " "+ items.shop +
                    "\n" + "Amount:" + " "+ items.amount +
                    "\n" + "Price:" + " "+ items.price.toString() + "kr" + "\n" + "\n"
        }

        var shareIntent = Intent().apply{
            this.action = Intent.ACTION_SEND
            this.putExtra(Intent.EXTRA_SUBJECT, "My shoppinglist")
            this.putExtra(Intent.EXTRA_TEXT, resStr)
            this.type = "text/plain"
        }
        startActivity(shareIntent)
    }




}