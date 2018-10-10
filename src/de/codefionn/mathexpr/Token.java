package de.codefionn.mathexpr;

public enum Token {
	NUM(' ', -1),
	VAR(' ', -1),

	EOF('\0', -1),

	ASSIGN('=', 1),

	OPAREN('(', -1),
	CPAREN(')', -1),

	ADD('+', 3),
	SUB('-', 3),
	MOD('%', 4),
	MUL('*', 6),
	DIV('/', 6);

	private char sign;
	private int prec;
	
	private Token(char sign, int prec) {
		this.sign = sign; this.prec = prec;
	}

	public char getSign() {
		return sign;
	}

	public int getPrecedence() {
		return prec;
	}
}
