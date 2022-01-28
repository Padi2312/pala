# What is Pala ?
Pala is the first try of creating a simple programming language
containing only basic arithmetic operators.

In a nutshell: At the moment it's just a simple calculator
not being able to handle floating numbers.

In the future it hopefully gets a small little programming language,
with basic operations like variable assignment etc.

# Grammar (of a basic calculator :D)
Expr --> Term ExprOpt \
ExprOpt --> "+" Term ExprOpt\
ExprOpt --> "-" Term ExprOpt\
ExprOpt --> e

Term -> Factor TermOpt\
TermOpt --> "*" Factor TermOpt\
TermOpt --> "/" Factor TermOpt\
TermOpt --> e

Factor --> "0" | "1" | "2" | "3" | "4" | "5" | "6" 
| "7" | "8" | "9" | "0" | "(" Expr ")"

