package com.xillioner.goodsmapper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xillioner.goodsmapper.databinding.ListTovarCarditemBinding

class TovarAdapter(
    private val mainActivity: MainActivity,
    private var tovarList: ArrayList<Tovar>
) : RecyclerView.Adapter<TovarAdapter.ListTovarCardViewHolder>() {

    inner class ListTovarCardViewHolder(listTovarCardviewBinding: ListTovarCarditemBinding) :
        RecyclerView.ViewHolder(listTovarCardviewBinding.root.rootView),
        View.OnClickListener
    {

        internal var tovarName = listTovarCardviewBinding.textViewTovarName
        internal var image = listTovarCardviewBinding.imageView
        internal var tovarDescription = listTovarCardviewBinding.textViewTovarDescription
        internal var price = listTovarCardviewBinding.textViewPrice
        internal var priceSymbol: String
        internal var tovarBuy = listTovarCardviewBinding.textViewTovarBuy

        init {
            listTovarCardviewBinding.textViewTovarBuy.setOnClickListener(this)
            listTovarCardviewBinding.cardView.setOnClickListener(this)

            priceSymbol =
                listTovarCardviewBinding.root.resources.getString(R.string.fa_solid_ruble_sign)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.textViewTovarBuy -> {
                    mainActivity.addToCart(adapterPosition, true)
                }
                R.id.cardView -> {
                    mainActivity.showTovar(adapterPosition)
                }
            }
        }
    }

    fun setFilteredList(searchedList: ArrayList<Tovar>){
        this.tovarList=searchedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTovarCardViewHolder {
        val itemBinding = ListTovarCarditemBinding.
        inflate(LayoutInflater.from(mainActivity), parent, false)
        return ListTovarCardViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListTovarCardViewHolder, position: Int) {
        val tovar = tovarList[position]
        holder.tovarName.text = tovar.name
        holder.image.setImageResource(android.R.drawable.ic_menu_report_image)
        holder.tovarDescription.text = tovar.description
        holder.price.text = "${tovar.price} ${holder.priceSymbol}"
        holder.tovarBuy.text = tovar.buyStatus
    }

    override fun getItemCount(): Int {
        return if (tovarList != null)
            tovarList.size
        else
        //error
            -1
    }
}