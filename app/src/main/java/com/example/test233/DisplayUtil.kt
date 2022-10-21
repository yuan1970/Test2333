package com.example.test233

import android.content.res.Resources
import android.view.View

/**
 * @author anyu
 * @date 2022/5/18-10:14
 * @desc
 */
object DisplayUtil {


     fun dip2px(resources: Resources, dipVal: Int): Int {
        val scale = resources.displayMetrics.density
        return (dipVal * scale + 0.5f).toInt()
    }

}