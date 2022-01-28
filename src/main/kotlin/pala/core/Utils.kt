package pala.core

import java.lang.Exception

object Utils {

    private const val arithmeticOperators = "+-*/"

    fun isArithmeticOperator(currentChar: Char): Boolean {
        return arithmeticOperators.contains(currentChar)
    }

    fun isArithmeticOperator(value: String): Boolean {
        return Regex(pattern = "[*\\-/+]").matches(value)
    }

    fun getTokenTypeFromArithmeticOp(value: String): TokenTypes {
        return when (value) {
            "+" -> {
                TokenTypes.PLUS
            }
            "-" -> {
                TokenTypes.MINUS
            }
            "*" -> {
                TokenTypes.TIMES
            }
            "/" -> {
                TokenTypes.DIVIDE
            }
            else -> {
                throw Exception("No such operator")
            }
        }
    }

    fun getTokenTypeFromArithmeticOp(value: Char): TokenTypes? {
        return getTokenTypeFromArithmeticOp(value.toString())
    }


    fun isNumber(char: Char): Boolean {
        return char.isDigit()
    }

    fun isNumber(value: String): Boolean {
        return value.matches("-?\\d+(\\.\\d+)?".toRegex())
    }

    fun isBracket(currentChar: Char): Boolean {
        return Terminals.brackets.contains(currentChar)
    }

    fun isLineSeparator(currentChar: Char): Boolean {
        return Terminals.lineTerminator.contains(currentChar)
    }
}