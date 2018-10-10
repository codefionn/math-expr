# Math-expr

Evaluate basic mathematical expressions.

## Regex

### Notice

"xyz": Uses POSIX basic regex

### Those of the language

input -> EMPTY | line input

line -> expr "\n\|\r"

expr ->
  NUM
| VAR
| (paren -> '(' expr ')')
| expr '+' expr
| expr '-' expr
| expr '\*' expr
| expr '/' expr
| expr '^' expr
