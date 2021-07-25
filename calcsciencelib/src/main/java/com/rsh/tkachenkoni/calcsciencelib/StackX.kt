package com.rsh.tkachenkoni.calcsciencelib

import android.util.Log

class StackX(private val maxSize: Int) {
    private val stackArray: Array<String?>
    private var top: Int
    fun push(j: String?) {
        stackArray[++top] = j
    }

    fun pop(): String? {
        return stackArray[top--]
    }

    fun peek(): String? {
        return stackArray[top]
    }

    fun isEmpty(): Boolean{
        return top == -1
    }


    fun size(): Int {
        return top + 1
    }

    fun peekN(n: Int): String? {
        return stackArray[n]
    }

    fun displayStack() {
        for (j in 0 until size()) {
            Log.d("stack: ", peekN(j)!!)
        }
    }

    init {
        stackArray = arrayOfNulls(maxSize)
        top = -1
    }
}
