package de.codefionn.mathexpr.terms;

import de.codefionn.mathexpr.*;

public class Variable implements Term {
	Parser parser; String varName;

	public Variable(Parser parser, String varName) {
		this.parser = parser;
		this.varName = varName;
	}

	public Parser getParser() {
		return parser;
	}

	public String getName() {
		return varName;
	}

	public double eval() {
		return parser.getVal(varName);
	}
}
