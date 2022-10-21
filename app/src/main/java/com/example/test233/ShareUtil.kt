package com.example.test233

import android.content.Context
import android.content.SharedPreferences

/**
 * @author anyu
 * @date 2022/9/21-8:14
 * @desc
 */
object ShareUtil {
    private var sps: SharedPreferences?=null
    private fun getSps(context: Context):SharedPreferences{
        if(sps==null){
            sps=context.getSharedPreferences("default",Context.MODE_PRIVATE)
        }
        return sps!!
    }
    fun putString(key:String,value:String?,context: Context){
        if(!value.isNullOrBlank()){
            var editor:SharedPreferences.Editor=getSps(context).edit()
            editor.putString(key,value)
            editor.commit()
        }
    }
    fun getString(key:String,context:Context):String?{
        if(!key.isNullOrBlank()){
            var sps:SharedPreferences=getSps(context)
            return sps.getString(key,null)
        }
        return null
    }
}