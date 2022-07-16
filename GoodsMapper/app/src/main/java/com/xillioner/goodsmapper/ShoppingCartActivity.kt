package com.xillioner.goodsmapper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xillioner.goodsmapper.databinding.ActivityShoppingCartBinding

class ShoppingCartActivity :AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var shoppingCartBinding: ActivityShoppingCartBinding

    private var shoppingCartList=ArrayList<Tovar>()
    private var recyclerView:RecyclerView?=null
    private var adapter:ShoppingCartAdapter?=null

    override fun onPause() {
        super.onPause()
        val jsonConverter=JSONConverter()
        val jsonString= jsonConverter.convertToString(shoppingCartList)
        val prefs=getSharedPreferences("Goods Mapper",Context.MODE_PRIVATE)
        val editor=prefs.edit()
        editor.putString("cart tovar list",jsonString)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shoppingCartBinding = ActivityShoppingCartBinding.inflate(layoutInflater)
        recyclerView=shoppingCartBinding.recyclerView

        setContentView(shoppingCartBinding.root)

        val prefs=getSharedPreferences("Goods Mapper", Context.MODE_PRIVATE)
        var jsonString =prefs.getString("cart tovar list","jsonString")
        var jsonConverter=JSONConverter()
        shoppingCartList=jsonConverter.convertToTovar(jsonString!!)

        adapter=ShoppingCartAdapter(this,shoppingCartList)

        val layoutManager=LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager=layoutManager
        recyclerView!!.itemAnimator=DefaultItemAnimator()
        recyclerView!!.adapter=adapter

        shoppingCartBinding.include.topNavigationView.selectedItemId = R.id.topMenuShoppingCart
        shoppingCartBinding.include.topNavigationView.setOnNavigationItemSelectedListener (this)
    }

    override fun onBackPressed() {
        backToMainActivity()
        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.topMenuItemCatalog -> {
                backToMainActivity()
                true
            }
            else -> {
                false
            }
        }
    }
    private fun backToMainActivity(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}