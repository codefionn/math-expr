package de.codefionn.mathexpr;

import static java.lang.Character.*;

import de.codefionn.mathexpr.terms.*;

public class Parser {
	String expr;
	
	int index = 0;
	int c;

	public Parser(String expr) {
		this.expr = expr;
	}

	public double getVal(String name) {
		return 0.0;
	}

	private int getchar() {
		if (index >= expr.length())
			return c = -1; // EOF
		return c = expr.charAt(index++);
	}

	String IdentifierStr = "";
	double NumVal = 0.0;

	private Token gettok() {
		// Skip spaces
		while (c == ' ' || c == '\t') getchar();

		if (isDigit(c)) {
			// NUM
			StringBuilder NumStr = new StringBuilder();
			NumStr.append((char) c);

			boolean dot = c == '.';
			while (getchar() != -1 && (isDigit(c) || c == '_' || (!dot && c == '.'))) {
				if (c != '_')
					NumStr.append((char) c);
				if (c == '.')
					dot = true;
			}

			NumVal = Double.parseDouble(NumStr.toString());
			return Token.NUM;
		}

		if (isLetter(c) || c == '_') {
			// VAR
			StringBuilder Str = new StringBuilder();
			Str.append((char) c);
			
			while (isLetterOrDigit(getchar()) || c == '_') {
				Str.append((char) c);
			}

			IdentifierStr = Str.toString();
			return Token.VAR;
		}

		if (c == -1)
			return Token.EOF;

		for (Token t : Token.values()) {
			if (t.getSign() == (char) c) {
				getchar();
				return t;
			}
		}

		System.err.println("Unknown/undefined character: " + c);
		return Token.EOF;
	}

	Token CurTok = Token.EOF;

	private Token getNextToken() {
		CurTok = gettok();
		System.out.println(CurTok.name());
		return CurTok;
	}

	private Term logError(String msg) {
		System.err.println("LogError: " + msg);
		return null;
	}

	// number
	private Term parseNumberExpr() {
		Term result = new NumberTerm(NumVal);
		getNextToken(); // eat NUM
		return result;
	}

	// paren ::= '(' expr ')'
	private Term parseParenExpr() {
		getNextToken(); // eat '('

		Term between = parseExpression();
		if (between == null)
			return between;

		if (CurTok.getSign() != ')')
			return logError("Expected ')'.");
		getNextToken(); // eat ')'
		return between;
	}

	private Term parseIdentifierExpr() {
		String IdName = IdentifierStr;

		getNextToken(); // eat Id

		if (CurTok.getSign() != '(') // a var. ref.
			return new Variable(this, IdName);

		return logError("Fn. call Not supported");
	}

	private Term parsePrimary() {
		if (CurTok == Token.VAR)
			return parseIdentifierExpr();
		else if (CurTok == Token.NUM)
			return parseNumberExpr();
		else if (CurTok == Token.OPAREN)
			return parseParenExpr();
		else
			return logError("Expected primary expression");
	}

	private Term parseBinOpRHS(int Prec, Term LHS) {
		while (true) {
			int TokPrec = CurTok.getPrecedence();

			// if operator precedence less than the current one, eat it.
			// otherwise we are done.
			if (TokPrec < Prec)
				return LHS;
			
			// Okay, we know this is a binary operator
			Token BinOp = CurTok;
			getNextToken(); // eat binary op

			Term RHS = parsePrimary();
			if (RHS == null)
				return null;

			int NextPrec = CurTok.getPrecedence();
			if (TokPrec < NextPrec) {
				RHS = parseBinOpRHS(TokPrec + 1, RHS);
				if (RHS == null)
					return null;
			}

			LHS = new BinaryOpTerm(BinOp, LHS, RHS);
		}
	}

	private Term parseExpression() {
		Term LHS = parsePrimary();
		if (LHS == null)
			return null;

		return parseBinOpRHS(0, LHS);
	}

	public Term main() {
		getchar();
		getNextToken();
		return parseExpression();
	}
}
