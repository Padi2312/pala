package pala.core

import pala.exceptions.ParserException

class Parser constructor(private val tokenList: List<Token>) {

    private var currentPosition = 0;
    private var lookAhead = tokenList[currentPosition]

    fun parse(): Node {
        return expr()
    }

    private fun expr(): Node {
        if (lookAhead.type == TokenTypes.NUMBER || lookAhead.type == TokenTypes.STARTBRAC) {
            val term = term(null)
            return exprOpt(term)
        } else {
            throw ParserException("Invalid token at position ${this.currentPosition} expected Type<${lookAhead.type.name}>")
        }
    }

    private fun exprOpt(node: Node?): Node {
        if (lookAhead.type == TokenTypes.PLUS || lookAhead.type == TokenTypes.MINUS) {
            val tokenType =
                Utils.getTokenTypeFromArithmeticOp(lookAhead.text)

            val value = match(tokenType)
            val term = term(null)
            val rest = exprOpt(node)
            return BinNode(value, rest, term)
        } else if (lookAhead.type == TokenTypes.EOF || lookAhead.type == TokenTypes.ENDBRAC) {
            return node!!
        } else {
            throw ParserException("Invalid input at $currentPosition")
        }
    }

    private fun term(node: Node?): Node {
        if (lookAhead.type == TokenTypes.NUMBER || lookAhead.type == TokenTypes.STARTBRAC) {
            val factor = factor()
            return termOpt(factor)
        }
        parserException(TokenTypes.NUMBER, TokenTypes.STARTBRAC)
        return null!!
    }

    private fun termOpt(node: Node?): Node {
        if (lookAhead.type == TokenTypes.TIMES || lookAhead.type == TokenTypes.DIVIDE) {
            val tokenType =
                Utils.getTokenTypeFromArithmeticOp(lookAhead.text)
            val value = match(tokenType)
            val factor = factor()
            val termOpt = termOpt(factor)
            return BinNode(value, node!!, termOpt)
        } else if (lookAhead.type == TokenTypes.PLUS || lookAhead.type == TokenTypes.MINUS
            || lookAhead.type == TokenTypes.EOF || lookAhead.type == TokenTypes.ENDBRAC
        ) {
            return node!!
        } else {
            parserException(TokenTypes.EOF)
            return null!!
        }
    }

    private fun factor(): Node {
        if (lookAhead.type == TokenTypes.STARTBRAC) {
            match(TokenTypes.STARTBRAC)
            val expr = expr()
            match(TokenTypes.ENDBRAC)
            return expr
        } else if (lookAhead.type == TokenTypes.NUMBER) {
            val value = match(TokenTypes.NUMBER)
            return OpNode(value, TokenTypes.NUMBER)
        } else {
            parserException(TokenTypes.NUMBER, TokenTypes.STARTBRAC)
            return null!!
        }
    }

    private fun match(type: TokenTypes): String {
        if (type != lookAhead.type) {
            throw ParserException("Expected Type<$type> but found Type<${lookAhead.type.name}> at $currentPosition")
        }
        val value = tokenList[currentPosition].text
        currentPosition++
        if (currentPosition < tokenList.size)
            lookAhead = tokenList[currentPosition]
        return value
    }

    private fun parserException(tokenTypes: TokenTypes, secondType: TokenTypes? = null) {
        if (secondType != null) {
            throw ParserException("Invalid token at position $currentPosition expected Type<$tokenTypes> or Type<$secondType>")
        } else {
            throw ParserException("Invalid token at position $currentPosition expected Type<$tokenTypes>")
        }

    }
}