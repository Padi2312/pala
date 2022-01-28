package pala.core

interface Visitor {
    fun visit(node: Node): Int
    fun visit(node: BinNode): Int
    fun visit(node: OpNode): Int
}