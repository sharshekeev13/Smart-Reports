package ru.inai.kursach_2_0.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreference (_context:Context){

    private val context = _context
    private val sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
    private val myEdit = sharedPreferences.edit()

    fun getLanguage() : String{
        return sharedPreferences.getString("language","default").toString()
    }
    fun putLanguage(language: String){
        myEdit.putString("language",language)
        myEdit.apply()
    }

    fun putId(id: Int?){
        myEdit.putInt("id",id!!)
        myEdit.apply()
    }

    fun getId() : Int{
        return sharedPreferences.getInt("id",0)
    }

    fun getNightMode() : Boolean{
       return sharedPreferences.getBoolean("NightMode",false)
    }

    fun putNightMode(type : Boolean){
        myEdit.putBoolean("NightMode",type)
        myEdit.apply()
    }


}