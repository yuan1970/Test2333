package com.example.test233

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

/**
 * @author anyu
 * @date 2022/5/31-10:04
 * @desc
 */
object TextUtils {

    fun matchValueUnitText(resources:Resources,content:String,unit:String,valueSize:Int,unitSize:Int): SpannableStringBuilder{

        val builder=SpannableStringBuilder(content)

        builder.setSpan(
            StyleSpan(Typeface.BOLD),0,
            content.indexOf(unit),
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        val valueHeight = DisplayUtil.dip2px(resources, valueSize)
        val unitHeight = DisplayUtil.dip2px(resources, unitSize)

        builder.setSpan(
            AbsoluteSizeSpan(valueHeight),0,
           content.indexOf(unit),
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        builder.setSpan(
            ForegroundColorSpan(Color.BLACK),0,
           content.indexOf(unit),
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        builder.setSpan(
            ForegroundColorSpan(Color.BLACK), content.indexOf(unit),
            content.indexOf(unit)+unit.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        builder.setSpan(
            AbsoluteSizeSpan(unitHeight), content.indexOf(unit),
            content.indexOf(unit)+unit.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

        builder.append("  ")
        return builder
    }

    fun longitudeAndLatitudeTransformationLines(data: String): String {
        return if (data.contains("N")){
            val aaa = data.split("N")
            aaa[0] + "\n" +"N"+ aaa[1]

        }else {
            val aaa = data.split("S")
            aaa[0] + "\n" +"S"+ aaa[1]
        }
    }
}