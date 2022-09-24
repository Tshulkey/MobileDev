package com.example.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lifecycles.databinding.ActivityDataBinding


class DataActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDataBinding
    val mortgage: Mortgage = Mortgage()     //MainActivity.mortgage
    val p: Prefs = Prefs(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("DataActivity", "onCreate Called")

        p.getPreferences(mortgage)          //make sure the DataActivity remembers what was before

        if(mortgage.getYears() == 10){
            binding.ten.isChecked = true
            binding.fifteen.isChecked = false
            binding.thirty.isChecked = false
        }
        else if(mortgage.getYears() == 15){
            binding.ten.isChecked = false
            binding.fifteen.isChecked = true
            binding.thirty.isChecked = false
        }
        else if(mortgage.getYears() == 30){
            binding.ten.isChecked = false
            binding.fifteen.isChecked = false
            binding.thirty.isChecked = true
        }

        binding.dataAmount.setText(mortgage.getAmount().toString())
        binding.dataRate.setText(mortgage.getRate().toString())

        //make sure only one button is selected at a time
        binding.ten.setOnClickListener(View.OnClickListener{
            binding.ten.isChecked = true
            binding.fifteen.isChecked = false
            binding.thirty.isChecked = false
        })

        binding.fifteen.setOnClickListener(View.OnClickListener{
            binding.fifteen.isChecked = true
            binding.ten.isChecked = false
            binding.thirty.isChecked = false
        })

        binding.thirty.setOnClickListener(View.OnClickListener{
            binding.thirty.isChecked = true
            binding.fifteen.isChecked = false
            binding.ten.isChecked = false
        })
    }

    override fun onStart() {
        super.onStart()
        // tag: The className       msg: the string message to print
        Log.i("DataActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("DataActivity", "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("DataActivity", "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("DataActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("DataActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("DataActivity", "onDestroy")
    }

    fun goBack(v:View?){
        updateMortgageObject()
        this.finish()                                       //will go back to MainActivity to the onResume method
        overridePendingTransition(R.anim.fade_in_and_scale, 0)
    }
    fun updateMortgageObject() {
//        val p = Prefs(this)
        val amountET = binding.dataAmount
        val rb10 = binding.ten
        val rb15 = binding.fifteen
        var years = 30
        if (rb10.isChecked)
            years = 10
        else if (rb15.isChecked)
            years = 15
        mortgage.setYears(years)
        val rateET = binding.dataRate
        val rateString:String = rateET.text.toString()
        val amountString = amountET.text.toString()
        try {
            val amount = amountString.toFloat()
            mortgage.setAmount(amount)
            val rate: Float = rateString.toFloat()
            mortgage.setRate(rate)
            p.setPreferences(mortgage)

        } catch (nfe: NumberFormatException) {
            mortgage.setAmount(100000.0f)
            mortgage.setRate(.035f)
        }
    }
}