package de.codefionn.mathexpr.terms;

import de.codefionn.mathexpr.*;

public class BinaryOpTerm extends BiOpTerm {
	Token Type;	

	public BinaryOpTerm(Token Type, Term LHS, Term RHS)	{
		super(LHS, RHS);
		this.Type = Type;
	}

	public double eval() {
		//System.out.println("Eval: BinOp(" + Type.getSign() + ")");
		
		if (Type == Token.ADD)
			return LHS.eval() + RHS.eval();
		if (Type == Token.SUB)
			return LHS.eval() - RHS.eval();
		if (Type == Token.MUL)
			return LHS.eval() * RHS.eval();
		if (Type == Token.DIV)
			return LHS.eval() / RHS.eval();
		return 0.0;
	}
}
