package com.example.test233

import android.view.View

/**
 * @author anyu
 * @date 2022/5/10-15:30
 * @desc
 */
abstract class OnNoDoubleClickListener : View.OnClickListener {

    private var mThrottleFirstTime: Long = 1000
    private var mLastClickTime: Long = 0

    constructor()
    constructor(throttleFirstTime: Long) {
        mThrottleFirstTime = throttleFirstTime
    }

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - mLastClickTime > mThrottleFirstTime) {
            mLastClickTime = currentTime
            onNoDoubleClick(v)
        }
    }

    abstract fun onNoDoubleClick(v: View?)

}