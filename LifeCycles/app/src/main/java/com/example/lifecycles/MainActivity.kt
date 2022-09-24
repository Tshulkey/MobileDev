package com.example.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.example.lifecycles.databinding.ActivityMainBinding
import android.util.Log


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val pf:Prefs = Prefs(this)
    val mortgage : Mortgage = Mortgage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MainActivity", "onCreate Called")

        pf.getPreferences(mortgage)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // tag: The className       msg: the string message to print
        Log.i("MainActivity", "onStart Called")
    }

    override fun onResume() {           //after DataActivity finish() it comes back here
        super.onResume()

        Log.i("MainActivity", "onResume Called")

        pf.getPreferences(mortgage)

        binding.years.text = mortgage.getYears().toString()
        binding.amount.text = mortgage.getFormattedAmount()
        binding.rate.text = (mortgage.getRate() * 100).toString() + "%"

        calculate()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy")
    }

    fun modifyData(view: View){

        val myIntent = Intent(this, DataActivity::class.java)
        this.startActivity(myIntent)
        overridePendingTransition(R.anim.slide_from_left,0)         //animation to DataActivity
    }

    fun calculate(){
        val monthPay = mortgage.formattedMonthlyPayment()
        val totalPay = mortgage.formattedTotalPayment()

        binding.payment.text = monthPay
        binding.total.text = totalPay
    }
}