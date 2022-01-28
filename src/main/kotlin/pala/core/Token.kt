package pala.core

data class Token(val type: TokenTypes, val text: String, val position: Int = 0)
