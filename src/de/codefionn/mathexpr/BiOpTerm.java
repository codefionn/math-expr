package de.codefionn.mathexpr;

public abstract class BiOpTerm implements Term {
	protected Term LHS, RHS;

	protected BiOpTerm(Term LHS, Term RHS) {
		this.LHS = LHS;
		this.RHS = RHS;
	}

	public Term getLHS() {
		return LHS;
	}

	public Term getRHS() {
		return RHS;
	}
}
