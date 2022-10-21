package com.example.test233

import android.util.Log

/**
 * @author anyu
 * @date 2022/5/10-13:36
 * @desc
 */
object ALog {

    private var debug = AppConfig.debug


    fun i(tag: String, content: String) {
        if (debug) {
            Log.i(tag, content)
        }
    }

    fun i(content: String) {
        i(getTag(), content)
    }

    fun v(tag: String, content: String) {
        if (debug) {
            Log.v(tag, content)
        }
    }

    fun v(content: String) {
        v(getTag(), content)
    }

    fun d(tag: String, content: String) {
        if (debug) {
            Log.d(tag, content)
        }
    }

    fun d(content: String) {
        d(getTag(), content)
    }

    fun w(tag: String, content: String) {
        if (debug) {
            Log.w(tag, content)
        }
    }

    fun w(content: String) {
        w(getTag(), content)
    }

    fun e(tag: String, content: String) {
        if (debug) {
            Log.e(tag, content)
        }
    }

    fun e(content: String) {
        e(getTag(), content)
    }


    private fun getTag(): String {
        val traceElements = Thread.currentThread().stackTrace
        var tag: String? = null
        tag = if (traceElements.size > 4) {
            val traceElement = traceElements[4]
            "(" + traceElement.fileName + ":" + traceElement.lineNumber + ")" + "  -->  " + traceElement.methodName + "  >>>>>"
        } else {
            "ALog"
        }
        return tag
    }

}