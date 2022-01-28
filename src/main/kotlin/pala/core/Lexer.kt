package pala.core

import pala.exceptions.LexerException

class Lexer constructor(private val input: String) {

    private val terminatorSymbol = ';'
    private val arithmeticOperators = "+-*/"
    private val terminalSymbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private var currentPosition: Int = 0

    fun lex(): List<Token> {
        val tokenList = mutableListOf<Token>()
        while (currentPosition < input.length) {
            val currentChar = lookAhead(0) ?: return tokenList
            val token = when {
                Utils.isNumber(currentChar) -> {
                    getNumberToken(currentChar)
                }
                isLetter(currentChar) -> {
                    Token(TokenTypes.EOF, currentChar.toString())
                }
                Utils.isArithmeticOperator(currentChar) -> {
                    getArithmeticOperatorToken(currentChar)
                }
                Utils.isBracket(currentChar) -> {
                    getBracketToken(currentChar)
                }
                else -> {
                    throw LexerException("Unexpected Token '$currentChar' at position ${currentPosition + 1}")
                }
            }
            tokenList.add(token)
        }
        tokenList.add(Token(TokenTypes.EOF, "<EOF>"))
        return tokenList
    }

    private fun getNumberToken(currentChar: Char): Token {
        var number = ""
        while (Utils.isNumber((lookAhead(0) ?: ' '))) {
            number += eatChar()
        }
        return Token(TokenTypes.NUMBER, number)
    }

    private fun getArithmeticOperatorToken(currentChar: Char): Token {
        val tokenType = Utils.getTokenTypeFromArithmeticOp(currentChar)
            ?: throw LexerException("Misinterpreted arithmetic operator")

        val value = eatChar().toString()
        return Token(tokenType, value)
    }

    private fun getBracketToken(currentChar: Char): Token {
        return if (currentChar == '(') {
            Token(TokenTypes.STARTBRAC, eatChar().toString())
        } else {
            Token(TokenTypes.ENDBRAC, eatChar().toString())
        }
    }

    private fun isLetter(char: Char): Boolean {
        return char.isLetter()
    }

    private fun isTerminatorSymbol(currentChar: Char): Boolean {
        return terminatorSymbol == currentChar
    }

    private fun eatChar(): Char {
        val result = input[currentPosition]
        currentPosition++
        return result
    }

    private fun lookAhead(position: Int): Char? {
        if (input.length == currentPosition + position) {
            return null
        }
        return input[currentPosition + position]
    }
}