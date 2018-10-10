package de.codefionn.mathexpr.terms;

import de.codefionn.mathexpr.Term;

public class NumberTerm implements Term {
	double NumVal;

	public NumberTerm(double NumVal) {
		this.NumVal = NumVal;
	}

	public double eval() {
		return NumVal;
	}
}
