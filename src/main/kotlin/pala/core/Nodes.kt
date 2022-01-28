package pala.core

sealed class Node(val value: String) {

    abstract fun accpet(visitor: Visitor)
}

class BinNode(value: String, val leftNode: Node, val rightNode: Node) : Node(value) {
    override fun accpet(visitor: Visitor) {
        visitor.visit(this)
    }
}

class OpNode(value: String, val type: TokenTypes) : Node(value) {
    override fun accpet(visitor: Visitor) {
        visitor.visit(this)
    }
}