package com.rsh.tkachenkoni.calcsciencelib.service

import java.util.*

class InToPost(  //private String input;
    private val input: ArrayList<String?>
) {
    private val theStack: StackX
    private val theStackServ: StackX
    private var output = ""
    private val outputList: ArrayList<String?>
    fun doTransList(): ArrayList<String?> {
        for (j in input.indices) {
            val ch = input[j]
            when (ch) {
                "+", "-" -> gotOperList(ch, 1)
                "*", "/" -> gotOperList(ch, 2)
                "(" -> theStack.push(ch)
                ")" -> gotParenList(ch)
                "^" -> gotOperList(ch, 2)
                "cos" -> gotOperList(ch, 3)
                "sin" -> gotOperList(ch, 3)
                "tg" -> gotOperList(ch, 3)
                "ctg" -> gotOperList(ch, 3)
                "ln" -> gotOperList(ch, 3)
                "log" -> gotOperList(ch, 3)
                else -> outputList.add(ch)
            }
        }
        while (!theStack.isEmpty()) { //извлечение оставшихся операторов
            outputList.add(theStack.pop()!!)
        }
        return outputList
    }

    fun gotOperList(opThis: String?, precx: Int) {
        while (!theStack.isEmpty()) {
            val opTop = theStack.pop()
            if (opTop === "(") {    //Если это (
                theStack.push(opTop) //Вернуть (
                break
            } else {
                var precy = 0
                precy =
                    if (opTop === "+" || opTop === "-") 1 else if (opTop === "*" || opTop === "/" || opTop === "^") 2 else 3
                if (precy < precx) {
                    theStack.push(opTop!!)
                    break
                } else {
                    outputList.add(opTop)
                }
            }
        }
        theStack.push(opThis!!)
    }

    fun gotParenList(ch: String?) {
        while (!theStack.isEmpty()) {
            val chx = theStack.pop()
            if (chx === "(") //если извлечен элеменет "("
                break //Прервать выполнение
            else  //Если извлечен опертора
                outputList.add(chx)
        }
    }

    //----String answer
    fun doTrans(): String {
        for (j in input.indices) {
            val ch = input[j]
            when (ch) {
                "+", "-" -> gotOper(ch, 1)
                "*", "/" -> gotOper(ch, 2)
                "(" -> theStack.push(ch)
                ")" -> gotParen(ch)
                "^" -> gotOper(ch, 3)
                "cos" -> theStack.push(ch)
                "sin" -> theStack.push(ch)
                "tg" -> theStack.push(ch)
                "ctg" -> theStack.push(ch)
                "ln" -> theStack.push(ch)
                "log" -> theStack.push(ch)
                else -> output = output + ch
            }
        }
        while (!theStack.isEmpty()) { //извлечение оставшихся операторов
            output = output + theStack.pop()
        }
        return output
    }

    fun gotOper(opThis: String?, precl: Int) {
        var precl = precl
        while (!theStack.isEmpty()) {
            val opTop = theStack.pop()
            if (opTop === "(") {    //Если это (
                theStack.push(opTop) //Вернуть (
                break
            } else {
                var prec2 = 0
                if (opTop === "+" || opTop === "-") precl = 1 else prec2 = 2
                output = if (prec2 < precl) {
                    theStack.push(opTop!!)
                    break
                } else output + opTop
            }
        }
        theStack.push(opThis!!)
    }

    fun gotParen(ch: String?) {
        while (!theStack.isEmpty()) {
            val chx = theStack.pop()
            output = if (chx === "(") //если извлечен элеменет "("
                break //Прервать выполнение
            else  //Если извлечен опертора
                output + chx
        }
    }

    init {
        val stackSize = input.size
        theStack = StackX(stackSize)
        theStackServ = StackX(stackSize)
        outputList = ArrayList()
    }
}
