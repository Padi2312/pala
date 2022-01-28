package pala.core

import pala.exceptions.InterpreterException

class Interpreter constructor(private val rootNode: Node) : Visitor {

    private var result = 0;

    @Throws(InterpreterException::class)
    fun interpret(): Int {
        if (rootNode !is BinNode) {
            throw InterpreterException("Too few arguments")
        } else {
            return visit(rootNode)

        }
    }

    override fun visit(node: BinNode): Int {
        when (val tokenType = Utils.getTokenTypeFromArithmeticOp(node.value)) {
            TokenTypes.PLUS -> {
                return visit(node.leftNode) + visit(node.rightNode)
            }
            TokenTypes.MINUS -> {
                return visit(node.leftNode) - visit(node.rightNode)
            }
            TokenTypes.TIMES -> {
                return visit(node.leftNode) * visit(node.rightNode)
            }
            TokenTypes.DIVIDE -> {
                return visit(node.leftNode) / visit(node.rightNode)
            }
            TokenTypes.EOF -> {
                throw InterpreterException("Unexpected Type<${tokenType}>")
            }
            else -> {
                throw InterpreterException("No such operation <${node.value}>")
            }
        }
    }

    override fun visit(node: OpNode): Int {
        if (!Utils.isNumber(node.value)) {
            throw InterpreterException("<${node.value}> is not a number.")
        }
        return node.value.toInt()
    }

    override fun visit(node: Node): Int {
        if (node is BinNode) {
            return visit(node)
        } else if (node is OpNode) {
            return visit(node)
        } else {
            throw InterpreterException("No such operation <${node.value}>")
        }
    }

}