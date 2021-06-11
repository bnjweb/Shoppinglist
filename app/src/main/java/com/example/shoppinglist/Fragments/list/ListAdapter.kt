package com.example.shoppinglist.Fragments.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.model.Items
import kotlinx.android.synthetic.main.showlist.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    //instantiate an empty list of items
    private var itemList = emptyList<Items>()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.showlist,parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.name_txt.text = currentItem.name
        holder.itemView.amount_txt.text = currentItem.amount.toString()
        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToItemFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
        holder.itemView.edit_btn.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToModifyFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        if (!currentItem.checkmark){
            holder.itemView.CardView.setCardBackgroundColor(Color.WHITE)
            holder.itemView.name_txt.setTextColor(Color.parseColor("#000000"))
            holder.itemView.amount_txt.setTextColor(Color.parseColor("#000000"))
        }
        else{
            holder.itemView.CardView.setCardBackgroundColor(Color.parseColor("#3CB371"))
            holder.itemView.name_txt.setTextColor(Color.parseColor("#ffffff"))
            holder.itemView.amount_txt.setTextColor(Color.parseColor("#000000"))
        }


        }

    fun setData(items: List<Items>){
        this.itemList = items
        notifyDataSetChanged()
    }
}