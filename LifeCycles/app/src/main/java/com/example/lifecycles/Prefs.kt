package com.example.lifecycles;

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Prefs (context1: Context) {
    private var context: Context? = context1
    private var amount:Float=200000.0f
    private var years: Int =15
    private var rate: Float =0.035f
    fun setPreferences(mort: Mortgage) {
        var s: SharedPreferences? =
            context!!.getSharedPreferences("Mortgage", Context.MODE_PRIVATE)
        var editor = s?.edit()
        editor!!.putFloat(Mortgage.PREFERENCE_AMOUNT, mort.getAmount())         //don't really need the if statement you can just use !! (if its not null it will do this)

        editor!!.putInt(Mortgage.PREFERENCE_YEARS, mort.getYears())

        editor!!.putFloat(Mortgage.PREFERENCE_RATE, mort.getRate())

        editor!!.commit()
    }
    fun getPreferences(mort: Mortgage)
    {
        var s: SharedPreferences? =context!!.getSharedPreferences("Mortgage", Context.MODE_PRIVATE)
        mort.setAmount(s!!.getFloat(Mortgage.PREFERENCE_AMOUNT, amount))            //also don't really need the if statement can just use !!
        mort.setYears(s!!.getInt(Mortgage.PREFERENCE_YEARS, years))
        mort.setRate(s!!.getFloat(Mortgage.PREFERENCE_RATE, rate))
    }
}
