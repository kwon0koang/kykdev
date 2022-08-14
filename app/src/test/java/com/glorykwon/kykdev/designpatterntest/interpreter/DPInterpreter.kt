package com.glorykwon.kykdev.designpatterntest.interpreter

import org.junit.Test

/**
 * 인터프리터 패턴
 * 문법 규칙을 클래스화 한 구조로써, 일련의 규칙으로 정의된 언어를 해석하는 패턴
 * 문법 규칙이 많아지면 복잡해지고 무거워지기 때문에 그럴 땐 차라리 파서/컴파일러 생성기를 쓰는 게 좋음
 * 언어 분석기라고 생각하면 되며, 스크립트나 컴파일러 문법 등이 있을 수 있음
 * 예로 SQL 구문이나 shell 커멘드 해석기, 통신 프로토콜 등이 있음
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

class DPInterpreter {

    @Test
    fun test() {
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

}