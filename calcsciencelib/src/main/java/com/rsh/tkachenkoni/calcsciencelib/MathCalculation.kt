package com.rsh.tkachenkoni.calcsciencelib

import android.util.Log
import java.util.*

class MathCalculation {
    var express = ArrayList<String>()
    var theStack: StackX? = null
    private var ANSWER_VALUE = 0.0
    protected fun Addition(F_VALUE: Double, S_VALUE: Double): Double {
        ANSWER_VALUE = F_VALUE + S_VALUE
        return ANSWER_VALUE
    }

    protected fun Substraction(F_VALUE: Double, S_VALUE: Double): Double {
        ANSWER_VALUE = F_VALUE - S_VALUE
        return ANSWER_VALUE
    }

    protected fun Multiplication(F_VALUE: Double, S_VALUE: Double): Double {
        ANSWER_VALUE = F_VALUE * S_VALUE
        return ANSWER_VALUE
    }

    protected fun Devidetion(F_VALUE: Double, S_VALUE: Double): Double {
        ANSWER_VALUE = F_VALUE / S_VALUE
        return ANSWER_VALUE
    }

    protected fun Degree(F_VALUE: Double, S_VALUE: Double): Double {
        if (S_VALUE == 0.0) ANSWER_VALUE = 1.0 else if (S_VALUE == 1.0) ANSWER_VALUE =
            F_VALUE else if (S_VALUE > 1) {
            ANSWER_VALUE = F_VALUE
            var i = 1
            while (i < S_VALUE) {
                ANSWER_VALUE = ANSWER_VALUE * F_VALUE
                i++
            }
        }
        return ANSWER_VALUE
    }

    protected fun SIN(num: Double): Double {
        if (MathStaticVar.KEY_RAD) {
            ANSWER_VALUE = Math.sin(num)
        } else if (MathStaticVar.KEY_DEGREE) {
            ANSWER_VALUE = Math.sin(num * MathStaticVar.PI / 180)
            if (ANSWER_VALUE < 0.000000000001) ANSWER_VALUE = 0.0
        }
        return ANSWER_VALUE
    }

    protected fun COS(num: Double): Double {
        if (MathStaticVar.KEY_RAD) {
            ANSWER_VALUE = Math.cos(num)
        } else if (MathStaticVar.KEY_DEGREE) {
            ANSWER_VALUE = Math.cos(num * MathStaticVar.PI / 180)
            if (ANSWER_VALUE < 0.000000000001) ANSWER_VALUE = 0.0
        }
        return ANSWER_VALUE
    }

    protected fun TG(num: Double): Double {
        if (MathStaticVar.KEY_RAD) {
            ANSWER_VALUE = Math.sin(num) / Math.cos(num)
        } else if (MathStaticVar.KEY_DEGREE) {
            ANSWER_VALUE =
                Math.sin(num * MathStaticVar.PI / 180) / Math.cos(num * MathStaticVar.PI / 180)
            if (ANSWER_VALUE < 0.000000000001) ANSWER_VALUE = 0.0
        }
        return ANSWER_VALUE
    }

    protected fun CTG(num: Double): Double {
        if (MathStaticVar.KEY_RAD) {
            ANSWER_VALUE = Math.cos(num) / Math.sin(num)
        } else if (MathStaticVar.KEY_DEGREE) {
            ANSWER_VALUE =
                Math.cos(num * MathStaticVar.PI / 180) / Math.sin(num * MathStaticVar.PI / 180)
            if (ANSWER_VALUE < 0.000000000001) ANSWER_VALUE = 0.0
        }
        return ANSWER_VALUE
    }

    protected fun LOG(num: Double): Double {
        return Math.log(num).also { ANSWER_VALUE = it }
    }

    protected fun LN(num: Double): Double {
        val e = 2.718281828
        return Math.log(num) / Math.log(e).also { ANSWER_VALUE = it }
    }

    protected fun SQRT(num: Double): Double {
        return Math.sqrt(num).also { ANSWER_VALUE = it }
    }

    fun ParsePostfixCalc(input: ArrayList<String?>): String {
        var answer = ""
        var error = false
        theStack = StackX(input.size)
        var ch: String?
        var j: Int
        var num1: Double
        var num2: Double
        var interAns: Double
        j = 0
        while (j < input.size) {
            ch = input[j] //чтение символа
            if (MathParser.checkNumber(ch)) {
                theStack!!.push(ch)
            } else {
                when (ch) {
                    "-" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        num1 = theStack!!.pop()!!.toDouble()
                        interAns = Substraction(num1, num2)
                    }
                    "+" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        num1 = theStack!!.pop()!!.toDouble()
                        interAns = Addition(num1, num2)
                    }
                    "*" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        num1 = theStack!!.pop()!!.toDouble()
                        interAns = Multiplication(num1, num2)
                    }
                    "/" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        num1 = theStack!!.pop()!!.toDouble()

                        //обработка при деление на 0
                        if (num2 != 0.0) interAns = Devidetion(num1, num2) else {
                            error = true
                            interAns = 0.0
                            break
                        }
                    }
                    "^" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        num1 = theStack!!.pop()!!.toDouble()
                        interAns = Degree(num1, num2)
                    }
                    "sin" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = SIN(num2)
                        Log.d("sin :", "" + interAns)
                    }
                    "cos" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = COS(num2)
                        Log.d("cos :", "" + interAns)
                    }
                    "tg" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = TG(num2)
                        Log.d("tg :", "" + interAns)
                    }
                    "ctg" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = CTG(num2)
                        Log.d("ctg :", "" + interAns)
                    }
                    "sqrt" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = SQRT(num2)
                    }
                    "log" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = LOG(num2)
                    }
                    "ln" -> {
                        num2 = theStack!!.pop()!!.toDouble()
                        interAns = LN(num2)
                    }
                    else -> interAns = 0.0
                }

                theStack!!.push("" + interAns) //занесение промежуточного результата в стек
            }
            if (error) {
                break
            }
            j++
        }
        interAns = theStack!!.pop()!!.toDouble()
        answer = if (!error) {
            "" + interAns
        } else {
            "ERROR"
        }
        return answer
    }
}
