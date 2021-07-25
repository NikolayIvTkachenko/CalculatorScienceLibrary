package com.rsh.tkachenkoni.calcsciencelib

import android.util.Log
import java.util.*

object MathParser {
    var degree_count = 0
    var operand1 = 0.0
    var multudevide = 0
    fun ParserMathExpression(expessionMath: String): ArrayList<String> {
        val expressions = ArrayList<String>()
        var str_serv = ""
        var expressionMathSub = ""
        var key_number = false
        var key_operate = false
        var key = false
        for (i in 0 until expessionMath.length) {
            //потом убрать после тестов, если не будет небходимости
            when (expessionMath.substring(i, i + 1)) {
                "(" -> {
                    str_serv = "("
                    key = true
                }
                ")" -> {
                    str_serv = ")"
                    key = true
                }
                "0" -> {
                    expressionMathSub += "0"
                    key_number = true
                    key = false
                }
                "1" -> {
                    expressionMathSub += "1"
                    key_number = true
                    key = false
                }
                "2" -> {
                    expressionMathSub += "2"
                    key_number = true
                    key = false
                }
                "3" -> {
                    expressionMathSub += "3"
                    key_number = true
                    key = false
                }
                "4" -> {
                    expressionMathSub += "4"
                    key_number = true
                    key = false
                }
                "5" -> {
                    expressionMathSub += "5"
                    key_number = true
                    key = false
                }
                "6" -> {
                    expressionMathSub += "6"
                    key_number = true
                    key = false
                }
                "7" -> {
                    expressionMathSub += "7"
                    key_number = true
                    key = false
                }
                "8" -> {
                    expressionMathSub += "8"
                    key_number = true
                    key = false
                }
                "9" -> {
                    expressionMathSub += "9"
                    key_number = true
                    key = false
                }
                "." -> {
                    expressionMathSub += "."
                    key_number = true
                    key = false
                }
                "-" -> if (i == 0) {
                    expressionMathSub += "-"
                    key_number = true
                } else {
                    str_serv = "-"
                    key = true
                }
                "+" -> {
                    str_serv = "+"
                    key = true
                }
                "*" -> {
                    str_serv = "*"
                    key = true
                }
                "/" -> {
                    str_serv = "/"
                    key = true
                }
                "^" -> {
                    str_serv = "^"
                    degree_count++
                    key = true
                }
                "s" -> {
                    expressionMathSub += "s"
                    key_operate = true
                    key = false
                }
                "i" -> {
                    expressionMathSub += "i"
                    key_operate = true
                    key = false
                }
                "n" -> {
                    expressionMathSub += "n"
                    key_operate = true
                    key = false
                }
                "c" -> {
                    expressionMathSub += "c"
                    key_operate = true
                    key = false
                }
                "o" -> {
                    expressionMathSub += "o"
                    key_operate = true
                    key = false
                }
                "t" -> {
                    expressionMathSub += "t"
                    key_operate = true
                    key = false
                }
                "g" -> {
                    expressionMathSub += "g"
                    key_operate = true
                    key = false
                }
                "l" -> {
                    expressionMathSub += "l"
                    key_operate = true
                    key = false
                }
                "q" -> {
                    expressionMathSub += "q"
                    key_operate = true
                    key = false
                }
                "r" -> {
                    expressionMathSub += "r"
                    key_operate = true
                    key = false
                }
                "P" -> {
                    expressionMathSub += "P"
                    key_operate = true
                    key = false
                }
                "I" -> {
                    expressionMathSub += "I"
                    key_operate = true
                    key = true
                }
            }
            //}
            if (key) {
                if (key_number) {
                    expressions.add(expressionMathSub)
                    expressionMathSub = ""
                    key_number = false
                }
                if (key_operate) {
                    if (expressionMathSub == "PI") expressionMathSub = "3.14159265358979"
                    expressions.add(expressionMathSub)
                    expressionMathSub = ""
                    key_operate = false
                }
                if (str_serv != "") {
                    expressions.add(str_serv)
                    str_serv = ""
                    key = false
                }
            } else {
                if (i == expessionMath.length - 1) {
                    if (key_number) {
                        expressions.add(expressionMathSub)
                        expressionMathSub = ""
                        key_number = false
                    }
                }
            }
        }
        return expressions
    }

    fun viewList(listexpression: ArrayList<String?>) {
        for (i in listexpression.indices) {
            Log.d(" list #$i = ", listexpression[i]!!)
        }
    }

    fun checkNumber(str: String?): Boolean {
        var key = true
        when (str) {
            "sin" -> key = false
            "cos" -> key = false
            "tg" -> key = false
            "ctg" -> key = false
            "sqrt" -> key = false
            "log" -> key = false
            "ln" -> key = false
            "-" -> key = false
            "+" -> key = false
            "*" -> key = false
            "/" -> key = false
            "^" -> key = false
            "(" -> key = false
            ")" -> key = false
            "PI" -> key = false
        }
        return key
    }

    //получаем строку с ответом, вычлиняем ответ, остальные операторы удаляем
    fun prepereAnswer(input: String): String {
        var answer = ""
        val parts = input.split("=").toTypedArray()
        answer = parts[1]
        return answer
    }

    //откорректировать ответ, если у нас нет дробной части, то убрать точку и запятую
    fun correctAnswer(input: String): String {
        var answer = ""
        var part1 = ""
        var part2 = ""
        part1 = input.substring(input.length - 1, input.length)
        part2 = input.substring(input.length - 2, input.length - 1)

        //Log.d("part1 = ", part1);
        //Log.d("part2 = ", part2);
        answer = if (part1 == "0" && part2 == ".") {
            input.substring(0, input.length - 2)
        } else {
            input
        }
        return answer
    }
}