package pala

import pala.core.Interpreter
import pala.core.Lexer
import pala.core.Parser

class Pala {

    fun execute(expression: String): Int {
        val lexer = Lexer(expression)
        val parser = Parser(lexer)
        val ast = parser.parse()
        return Interpreter(ast).interpret()
    }
}