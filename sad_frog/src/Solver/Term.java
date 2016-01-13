package Solver;

public class Term {
	
	double coefficient;
	String variable;
	int exponent;
	boolean constant;//this is true if a Term has no variable expression
	//TODO: Write getters for all of the above fields

	public Term(double coefficient, String var, int exp){
		//set the value of all the fields
		//if variable is non-empty, sets constant to 'false'
		this.coefficient = coefficient;
		this.variable = var;
		this.exponent = exp;
		this.constant = !var.equals("") ? false : true;
		
		if (coefficient == 0.0) {
			variable = "";
			exponent = 0;
		}
	}

	/**
	 *a constructor for constant: 
	 */
	Term(double constant) {
		//sets coefficient to constant, variable to "", exponent to "0" and constant to 'true')
		this.coefficient = constant;
		this.variable = "";
		this.exponent = 0;
		this.constant = true;
	}
	
	/**
	 *Write getters for each field
	 *Note that the getter for the boolean should be called 'isConstant'
	 */ 
	public double getCoefficient() {
		return coefficient;
	}

	public String getVariable() {
		return variable;
	}

	public int getExponent() {
		return exponent;
	}

	public boolean isConstant() {
		return constant;
	}

	public Term getAddInverse(){
		//returns the additive inverse of this Term
		return new Term(-1*coefficient, variable, exponent);
	}

	public int getDegree(){
		//returns the exponent
		return exponent;
	}

	public boolean isPositive(){
		//returns true if the coefficient is positive (or zero), false otherwise
		return coefficient > -1 ? true : false;
	}	

	public String toString(){
		/**
		 *Some tips to consider:
		 *doubles always print with trailing zeros (i.e. 2.0) This is not desirable
		 *If a term has a coefficient of 1 or -1, the 1 should not be shown. 
		 *If a term has an exponent of 1, the 1 should not be shown
		 *For exponents, use '^'. The GUI will change it into superscript.
		 */
		if (constant) {
			if(coefficient % 1.0 == 0.0) return "" + (int)coefficient;
			else return "" + coefficient;
		}
		String str = new String();
		if (Math.abs(coefficient) == 1.0) {
			if (coefficient == -1.0) str += "-";
		} else {
			if (coefficient % 1.0 == 0.0) str += (int)coefficient;
			else str += coefficient;
		}
		
		str += variable;
		if (Math.abs(exponent) != 1) str += "^" + exponent;
		
		return str;
	}

	
	public static boolean areLikeTerms(Term s, Term t){
		if( s.getExponent() == t.getExponent() && s.getVariable().equals(t.getVariable())) return true;
		return false;
	}

	/**
	* returns a new Term with same variable and exponent as s and t but combined coefficient
	* @param s
	* @param t
	* @return
	*/

	public static Term combine(Term s, Term t){
		return new Term(s.getCoefficient() + t.getCoefficient(), s.getVariable(), s.getExponent());
	}

	public void setCoefficient(double d) {
		this.coefficient = d;
	}
}