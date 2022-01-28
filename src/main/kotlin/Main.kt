import pala.Pala

fun main(args: Array<String>) {
    /*val lexer = Lexer("1-1*3;")
    val tokenList = lexer.lex()

    val parser = Parser(tokenList)
    val ast = parser.parse()

    val interpreter = Interpreter(ast)
    val result = interpreter.interpret()*/

    val pala = Pala()

    println("FIRST: " + pala.execute("((1++2*2)/5)*3").toString())
}