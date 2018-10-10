package de.codefionn.mathexpr;

public class ProgramMain {
	public static final void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Mathematical expressions expected.");
			System.exit(1);
		}

		for (final String s : args) {
			System.out.println("> " + s);
			Term t = new Parser(s).main();
			if (t == null)
				System.exit(1);
			System.out.println(t.eval());
		}
	}
}
