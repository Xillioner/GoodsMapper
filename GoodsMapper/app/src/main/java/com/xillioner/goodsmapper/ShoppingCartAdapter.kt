package com.xillioner.goodsmapper

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xillioner.goodsmapper.databinding.ShoppingCartListitemBinding

class ShoppingCartAdapter():RecyclerView.Adapter<ShoppingCartAdapter.ListShoppingCartTovarViewHolder>() {
    inner class ListShoppingCartTovarViewHolder(shoppingCartListitemBinding:ShoppingCartListitemBinding):
        RecyclerView.ViewHolder(shoppingCartListitemBinding.root.rootView),
        View.OnClickListener
    {
        internal var tovarNameSC=shoppingCartListitemBinding.textViewNameSC
        internal var tovarAmountSC=shoppingCartListitemBinding.textViewAmountSC
        internal var tovarImageSC=shoppingCartListitemBinding.imageView3
        internal var tovarPriceSC=shoppingCartListitemBinding.textViewPriceSC


        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListShoppingCartTovarViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListShoppingCartTovarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}