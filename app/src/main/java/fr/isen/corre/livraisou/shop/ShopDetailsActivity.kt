package fr.isen.corre.livraisou.shop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import fr.isen.corre.livraisou.*
import fr.isen.corre.livraisou.databinding.ActivityDetailBinding

class ShopDetailsActivity: AppCompatActivity() {
    private lateinit var  binding: ActivityDetailBinding
    val TAG = "ShopDetailsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shopName = intent.getStringExtra(SHOP_EXTRA)
        val shop = shopName?.let {
            shopFromName(it) }
        shop?.let {
            binding.title.text = it.title
        }
        listenclick()
    }

    private fun shopFromName(shopName: String): Shop?
    {
        for(shop in shopList)
        {
            if(shop.title == shopName)
                return shop
        }
        return null
    }
    private fun changeActivityToDeco() {
        val intent = Intent (this, ProductsDECOActivity::class.java)
        startActivity(intent)
    }
    private fun changeActivityToFL() {
        val intent = Intent (this, ProductsFLActivity::class.java)
        startActivity(intent)
    }
    private fun changeActivityToPN() {
        val intent = Intent (this, ProductsPNctivity::class.java)
        startActivity(intent)
    }
    private fun changeActivityToPA() {
        val intent = Intent (this, ProductsPAActivity::class.java)
        startActivity(intent)
    }
    private fun changeActivityToVIANDE() {
        val intent = Intent (this, ProductsVIANDEActivity::class.java)
        startActivity(intent)
    }
    private fun changeActivityToPDM() {
        val intent = Intent (this, ProductsPDMActivity::class.java)
        startActivity(intent)
    }
    private fun listenclick(){
        binding.buttonDeco.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToDeco()

        }
        binding.buttonPDM.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToPDM()

        }
        binding.buttonPA.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToPA()

        }
        binding.buttonPN.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToPN()

        }
        binding.buttonFL.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToFL()

        }
        binding.buttonViande.setOnClickListener{
            Log.d("test","test1322123")
            Toast.makeText(this, "deco",  Toast.LENGTH_SHORT)
            changeActivityToVIANDE()

        }
    }

}