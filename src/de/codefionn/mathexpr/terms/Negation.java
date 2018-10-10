package de.codefionn.mathexpr.terms;

import de.codefionn.mathexpr.Term;

public class Negation implements Term {
	Term term;

	public Negation(Term term) {
		this.term = term;
	}

	public double eval() {
		return -term.eval();
	}
}
