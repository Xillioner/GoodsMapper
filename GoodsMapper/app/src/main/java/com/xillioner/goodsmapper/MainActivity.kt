package com.xillioner.goodsmapper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xillioner.goodsmapper.databinding.ActivityMainBinding
import com.xillioner.goodsmapper.databinding.ShowTovarBinding
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var showTovarBinding: ShowTovarBinding

    private var tovarList = ArrayList<Tovar>()

    private var recyclerView: RecyclerView? = null
    private var adapter: TovarAdapter? = null

    private var tovarId: Int = -1

    private var currentAmmountTovar: Int = 1

    private var showTovarFlag = false
    private var mainActivityFlag = false

    //Свойство расширяет editText
    private val EditText.intValue: Int
        get() = text.toString().toInt()

    override fun onPause() {
        super.onPause()
        if (!showTovarFlag)
            showTovarFlag = true
    }

    override fun onResume() {
        super.onResume()
        Log.i("info", "onResume")
        if (showTovarFlag) {
            showTovar(tovarId)
            setContentView(showTovarBinding.root)
        }
    }

    override fun onBackPressed() {
        if (showTovarFlag) {
            mainActivityFlag = true
            showTovarFlag = false
            tovarId = -1
            currentAmmountTovar = 1
            setContentView(this.activityMainBinding.root)

        } else if (mainActivityFlag) {
            mainActivityFlag = false
            super.onBackPressed()
        }
        Log.i("info", "OnBackPressed")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityFlag = true
        Log.i("info", "OnCreate")
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = activityMainBinding.recyclerViewCatalog


        setContentView(activityMainBinding.root)

        val tovarItem = Tovar()
        tovarItem.name = "Велосипед 1"
        tovarItem.description = "Трёх-колёсный"
        tovarItem.price = "1000"
        tovarItem.buyStatus = "КУПЛЮ СЕЙЧАС"

        val tovarItem1 = Tovar()
        tovarItem1.name = "Велосипед 2"
        tovarItem1.description = "Трёх-колёсный"
        tovarItem1.price = "5000"
        tovarItem1.buyStatus = "КУПЛЮ СЕЙЧАС"

        val tovarItem2=Tovar()
        tovarItem2.name="Bike_red"
        tovarItem2.price="10000"
        tovarItem2.description="Красный двухколёсный"
        tovarItem2.buyStatus="Куплю сейчас"

        tovarList.add(tovarItem)
        tovarList.add(tovarItem1)
        tovarList.add(tovarItem2)



        adapter = TovarAdapter(this, tovarList)

        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter

        showTovarBinding = ShowTovarBinding.inflate(layoutInflater)
        showTovarBinding.textViewBuy.setOnClickListener(this)
        showTovarBinding.textViewAddTovar.setOnClickListener(this)
        showTovarBinding.textViewSubstractTovar.setOnClickListener(this)
        activityMainBinding.includeTop.topNavigationView.setOnNavigationItemSelectedListener(this)
        activityMainBinding.includeTop.topNavigationView.setOnNavigationItemSelectedListener(this)
        activityMainBinding.includeBottom.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        activityMainBinding.includeBottom.searchView.setOnQueryTextListener(this)

        activityMainBinding.includeTop.topNavigationView.selectedItemId=R.id.topMenuItemCatalog


    }

    private fun changeAmmountTovar() {
        showTovarBinding.editTextCurrentAmmountTovar.setText(currentAmmountTovar.toString())
    }

    fun addToCart(adapterPosition: Int, showDialog: Boolean) {
        when (showDialog) {
            true -> {
                Log.i("info", "Добавить товар $adapterPosition в корзину?")
            }
            false -> {
                Toast.makeText(
                    this,
                    "Товар был добавлен в корзину, в количестве $currentAmmountTovar шт.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.i(
                    "info",
                    "Товар $adapterPosition добавлен в корзину, в количестве $currentAmmountTovar."
                )
                onBackPressed()
            }
        }
    }

    fun showTovar(adapterPosition: Int) {
        Log.i("info", "Показать товар $adapterPosition")
        showTovarFlag = true
        tovarId = adapterPosition
        showTovarBinding.textViewName.text = tovarList[adapterPosition].name
        showTovarBinding.textViewDescription.text = tovarList[adapterPosition].description
        showTovarBinding.textViewShowPrice.text =
            tovarList[adapterPosition].price +
                    showTovarBinding.root.resources.getString(R.string.fa_solid_ruble_sign)
        changeAmmountTovar()
        val viewShowTovar = showTovarBinding.root
        setContentView(viewShowTovar)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.textViewBuy -> {
                try {
                    currentAmmountTovar = showTovarBinding.editTextCurrentAmmountTovar.intValue
                    this.addToCart(tovarId, false)
                } catch (ex: Exception) {
                    Log.i(
                        "error",
                        "Ошибка showtovarbinding-textViewBuy-setOnClickListner: ${ex.cause}"
                    )
                    Toast.makeText(this, "Введи корректное количество товара.", Toast.LENGTH_SHORT)
                        .show()
                } finally {
                    currentAmmountTovar = 1
                    changeAmmountTovar()
                }
            }

            R.id.textViewAddTovar -> {
                ++currentAmmountTovar
                changeAmmountTovar()
            }

            R.id.textViewSubstractTovar -> {
                if (currentAmmountTovar > 1) {
                    --currentAmmountTovar
                    changeAmmountTovar()
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.topMenuItemHome->{}
            R.id.topMenuItemCatalog->{}
            R.id.topMenuItemProfile->{}
            R.id.bottomMenuFiltre->{}
            R.id.bottomMenuSearch->{
                activityMainBinding.includeBottom.searchView.isGone=if (activityMainBinding.includeBottom.searchView.visibility==View.GONE)
                    false
                else
                    true
            }
        }
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        searchInArray(p0)
        return true
    }

    private fun searchInArray(p0: String?) {
        var searchedArray:ArrayList<Tovar> =ArrayList<Tovar>()
        for ( tovar in tovarList){
            if(tovar.name!!.lowercase().contains(p0!!.lowercase())){
                searchedArray.add(tovar)
            }
        }
        if(searchedArray.isEmpty()){
            Toast.makeText(this,"Товар не найден",Toast.LENGTH_SHORT).show()
        }else{
            adapter?.setFilteredList(searchedArray)
        }
    }
}
