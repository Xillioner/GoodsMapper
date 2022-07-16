package com.xillioner.goodsmapper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xillioner.goodsmapper.databinding.ShoppingCartListitemBinding

class ShoppingCartAdapter(
    private val shoppingCartActivity: ShoppingCartActivity,
    private var cartTovarList:ArrayList<Tovar>
):RecyclerView.Adapter<ShoppingCartAdapter.ListShoppingCartTovarViewHolder>() {
    inner class ListShoppingCartTovarViewHolder(shoppingCartListitemBinding:ShoppingCartListitemBinding):
        RecyclerView.ViewHolder(shoppingCartListitemBinding.root.rootView),
        View.OnClickListener
    {
        internal var tovarNameSC=shoppingCartListitemBinding.textViewNameSC
        internal var tovarAmountSC=shoppingCartListitemBinding.textViewAmountSC
        internal var tovarImageSC=shoppingCartListitemBinding.imageView3
        internal var tovarPriceSC=shoppingCartListitemBinding.textViewPriceSC

        init {
            shoppingCartListitemBinding.cartItemView.setOnClickListener(this)
            //инициализировать ClickListener
        }

        override fun onClick(p0: View?) {
            when(p0?.id){
                R.id.cartItemView->{
                    //показать окно для изменения.
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ListShoppingCartTovarViewHolder {
        val itemBinding=ShoppingCartListitemBinding.
        inflate(LayoutInflater.from(shoppingCartActivity),parent, false)
        return  ListShoppingCartTovarViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListShoppingCartTovarViewHolder, position: Int) {
        val tovar=cartTovarList[position]
        holder.tovarNameSC.text=tovar.name
        holder.tovarImageSC.setImageResource(android.R.drawable.ic_menu_report_image)
        holder.tovarAmountSC.text=tovar.amount.toString()+"шт."
        holder.tovarPriceSC.text=tovar.price.toString()

    }

    override fun getItemCount(): Int {
        return if(cartTovarList!=null)
            cartTovarList.size
        else
            //error
            -1
    }
}