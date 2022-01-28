import pala.Pala

fun main(args: Array<String>) {
    val calculation = "((1+2*2)/5)*3"
    println(calculation + " = " + Pala().execute(calculation).toString())
}