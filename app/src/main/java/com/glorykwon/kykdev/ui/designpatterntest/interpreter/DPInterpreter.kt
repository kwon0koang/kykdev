package com.glorykwon.kykdev.ui.designpatterntest.interpreter

/**
 * 인터프리터 패턴
 */
interface Expression {
    fun interpret(value: String): Boolean
}

class AndExpression(
    val expr1: Expression,
    val expr2: Expression
): Expression {
    override fun interpret(value: String): Boolean = expr1.interpret(value) && expr2.interpret(value)
}

class OrExpression(
    val expr1: Expression,
    val expr2: Expression
): Expression {
    override fun interpret(value: String): Boolean = expr1.interpret(value) || expr2.interpret(value)
}

class TerminalExpression(
    val data: String
): Expression {
    override fun interpret(value: String): Boolean = data.contains(value)
}

fun main(){
    val expr1 = TerminalExpression("가나다라마")
    val expr2 = TerminalExpression("가나ABCDE")
    val andExpr = AndExpression(expr1, expr2)
    val orExpr = OrExpression(expr1, expr2)

    println(expr1.interpret("가나").toString())       //true
    println(expr1.interpret("가나다라마바사").toString())      //false

    println(expr2.interpret("ABC").toString())      //true
    println(expr2.interpret("ABCDEFGHI").toString())        //false

    println(andExpr.interpret("가나").toString())     //true
    println(andExpr.interpret("가나다라").toString())       //false

    println(orExpr.interpret("가나").toString())      //true
    println(orExpr.interpret("A").toString())       //true
    println(orExpr.interpret("F").toString())       //false
}