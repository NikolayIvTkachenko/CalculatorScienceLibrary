package com.rsh.tkachenkoni.calcsciencelib.executions

import android.widget.Toast
import com.rsh.tkachenkoni.calcsciencelib.service.InToPost
import com.rsh.tkachenkoni.calcsciencelib.service.MathCalculation
import com.rsh.tkachenkoni.calcsciencelib.service.MathParser
import com.rsh.tkachenkoni.calcsciencelib.service.MathStaticVar
import java.util.*

object Calculator {

    fun calculate(strValues: String):String{
        var result = ""
        val count_brakets_open = 0

        if (count_brakets_open == 0) {
            var serv_list = ArrayList<String?>()
            serv_list = MathParser.ParserMathExpression(strValues)
            MathParser.viewList(serv_list)
            val itp = InToPost(serv_list)
            MathParser.viewList(itp.doTransList())
            val mc = MathCalculation()
            result = strValues + "=" + MathParser.correctAnswer(
                mc.ParsePostfixCalc(itp.doTransList())
            )
            return result
        } else {
            return "Open brackets exists"
        }
    }
}