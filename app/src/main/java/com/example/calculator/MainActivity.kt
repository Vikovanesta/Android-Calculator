package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.Stack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun InputNilai(view: View) {
        val panel = findViewById<TextView>(R.id.panel)
        when((view as Button).text.toString()){
            "AC"-> {
                panel.text = ""
            }
            "DEL"-> {
                if (panel.text.last() == ' ') {
//                    panel.text = panel.text.dropLast(3)
                    panel.text = panel.text.dropLastWhile { !it.isDigit() }
                } else {
                    panel.text = panel.text.dropLast(1)
                }
            }
            "+", "-", "/", "x"-> {
                if (panel.text.last() == ' ') {
                    panel.text = panel.text.dropLastWhile { !it.isDigit() }
                    panel.text = "${panel.text} ${view.text} "
                } else {
                    panel.text = "${panel.text} ${view.text} "
                }
            }
            else->{
                panel.text = "${panel.text}${view.text}"
            }
        }
    }

    fun calculate(view: View) {
        val panel = findViewById<TextView>(R.id.panel)
        val expression = getExpression(panel.text.toString())
        val stack = Stack<Int>()

        for (token in expression) {
            if (token !in "+-*/") {
                stack.push(token.toInt())
            } else {
                val operand2 = stack.pop()
                val operand1 = stack.pop()
                when (token) {
                    "+" -> stack.push(operand1 + operand2)
                    "-" -> stack.push(operand1 - operand2)
                    "*" -> stack.push(operand1 * operand2)
                    "/" -> stack.push(operand1 / operand2)
                }
            }
        }


//        panel.text = operand1.toString()
        panel.text = stack.pop().toString()
//        panel.text = expression.toString()
    }

    private fun getExpression(expression: String): List<String> {
        var expression = expression
        expression = expression.replace(Regex("x"), "*")
        return infixToPostfix(expression)
    }

    private fun infixToPostfix(infixExpression: String): List<String> {
        val operatorPrecedence = mapOf(
            "+" to 1,
            "-" to 1,
            "*" to 2,
            "/" to 2,
            "^" to 3
        )
        val postfixExpression = mutableListOf<String>()
        val stack = Stack<String>()
        val infixExpression = infixExpression.split(" ")

        for (token in infixExpression) {
            when {
                token.contains("[0-9]".toRegex()) -> postfixExpression.add(token)
                token == "(" -> stack.push(token)
                token == ")" -> {
                    while (!stack.isEmpty() && stack.peek() != "(") {
                        postfixExpression.add(stack.pop())
                    }
                    stack.pop() // Pop the '('
                }
                else -> {
                    while (!stack.isEmpty() && stack.peek() != "(" &&
                        operatorPrecedence.getValue(token) <= operatorPrecedence.getOrDefault(stack.peek(), 0)
                    ) {
                        postfixExpression.add(stack.pop())
                    }
                    stack.push(token)
                }
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == "(") {
                throw IllegalArgumentException("Mismatched parentheses in the infix expression")
            }
            postfixExpression.add(stack.pop())
        }

        return postfixExpression
    }
}

