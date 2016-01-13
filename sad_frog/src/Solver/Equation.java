package Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Equation {
	
	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;
	private boolean cancelRight;
	private ArrayList<Double> solution;
	//TODO: Fix isLinear and isQuadratic

	public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
		//sets values of leftSide and rightSide only
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	public ArrayList<Term> getLeftSide() {
		return leftSide;
	}

	public ArrayList<Term> getRightSide() {
		return rightSide;
	}

	public boolean isCancelRight() {
		return cancelRight;
	}

	public ArrayList<Double> getSolution() {
		return solution;
	}

	public boolean isLinear(){
		//returns 'true' if this equation is linear
		//and every term is either constant or has the same variable
		
		/*
		if (checkConstAndVar()) return true;
		
		ArrayList<Term> all = new ArrayList<Term>(leftSide);
		all.addAll(rightSide);
		
		for (Term t: all) {
			if (t.getExponent() > 1) return false;
		}
		*/
	
		if (!checkConstAndVar()) return false;
		
		ArrayList<Term> all = new ArrayList<Term>(leftSide);
		all.addAll(rightSide);
		
		if (getHighestDegreeTerm(all).exponent != 1) return false;
		
		return true;
	}

	public boolean isQuadratic(){
		//returns 'true' if this equation is quadratic
		//and every term is either constant or has the same variable
		
		/*
		if (checkConstAndVar()) return true;
		
		ArrayList<Term> all = new ArrayList<Term>(leftSide);
		all.addAll(rightSide);
		
		boolean pass = false;
		for (Term t: all) {
			if (t.getExponent() == 2) pass = true;
			if (t.getExponent() > 2) return false;
		}
		
		return pass ? true : false;
		*/
		
		if (!checkConstAndVar()) return false;
		
		ArrayList<Term> all = new ArrayList<Term>(leftSide);
		all.addAll(rightSide);
		if (getHighestDegreeTerm(all).exponent != 2) return false;
		
		return true;
	}
	
	public boolean checkConstAndVar() {
		// Checks if both sides have at least one term
		if (leftSide.size() < 1 || rightSide.size() < 1) return false;
		
		ArrayList<Term> all = new ArrayList<Term>(leftSide);
		all.addAll(rightSide);
		
		if (getHighestDegreeTerm(all).getExponent() < 1) return false;
		
		String variable = getHighestDegreeTerm(all).getVariable();
		for (Term t: all)
			if (!t.getVariable().equals(variable) && !t.getVariable().equals("")) return false;
	
		return true;
	}
	
	public boolean isSolveable(){
		//returns 'true' if this equation is linear or quadratic, 'false' otherwise 
		//(because in this project we are not programming a means of solving anything other than linear and quadratic equations)
		return isLinear() || isQuadratic() ? true : false;
	}

	public boolean cancelRight(){
		//sets the value of cancelRight and
		//returns 'true' if the equation should be solved by subtracting terms from the right side, false if it is better to subtract terms on the left side		
		Term highestLeft = getHighestDegreeTerm(leftSide);
		Term highestRight = getHighestDegreeTerm(rightSide);
		
		if (highestLeft.exponent != highestRight.exponent)
			return highestLeft.exponent > highestRight.exponent ? true : false;
		else
			return highestLeft.coefficient > highestRight.coefficient ? true : false;
	}

	public String toString(){
		/**
		 *Take your time on this method!
		 *There are many things to consider:
		 *Every terms should be separated by a '+' UNLESS it has a negative coefficient. 
		 *When a term is negative, the negative sign will appear as a minus.
		 */
		String str = new String();
		str += getSideString(leftSide) + " = " + getSideString(rightSide);
		
		return str;
	}
	
	private String getSideString(ArrayList<Term> side) {
		if (side.size() == 0) return "0";
		String str = new String();
		str += side.get(0).toString();
		for (int i = 1; i < side.size(); i++) {
			if (side.get(i).getCoefficient() < 0.0) str += " - " + side.get(i).getAddInverse().toString();
			else str += " + " + side.get(i).toString();
		}
		
		return str;
	}

	public static Term getHighestDegreeTerm(ArrayList<Term> side){
		//returns the term in the ArrayList that is the highest degree.
		//example
		//3x^2 + 4x^3 - 12x + x^2
		//will return 4x^3
		int highest = 0;
		for (int i = 1; i < side.size(); i++)
			if (side.get(i).getDegree() > side.get(highest).getDegree()) highest = i;
		
		return side.get(highest);
	}
	
	public void toZeroOnOneSide() {
		ArrayList<Term> temp = new ArrayList<Term>(cancelRight() ? rightSide : leftSide);
		// ArrayList<Term> temp = cancelRight() ? rightSide : leftSide;
		
		int i = temp.size() - 1;
		
		while (i > -1) {
			Term t = getHighestDegreeTerm(temp);
			temp.remove(t);
			t = t.getAddInverse();
			leftSide.add(t);
			rightSide.add(new Term(t.getCoefficient(), t.getVariable(),t.getExponent()));
			i--;
		}
	}
	
	/**
	* 
	* @param side - simplifies the specified side of the equation
	* Notes: This method should check every Term on the specified side of the equation 
	* with every other Term to check if they are like terms (use Term.areLikeTerms(Term s, Term t)
	* If they are, it should reassign the current Term to the combined result (use Term.combine(Term s, Term t)
	* and remove the one with which it combined.
	* Finally, if the resulting term has a coefficient of zero should be removed.
	* 
	* For example, Suppose side contains 5x + 3 -5x. Call the three terms a, b and c, respectively
	* 1. It checks to see if 5x and 3 (a and b) are like terms, they are not
	* 2. It checks to see if 5x and -5x (a and c) are like terms, they are
	* 3. Since 5x and -5x are like terms, a = Term.cobine(a, c) then leftSide.remove(c)
	* 4. Now side contains 0x + 3. Since term a has a coefficient of zero, remove it.
	* 5. Now side contains 3. 
	* 
	* ONE MORE NOTE: Since you will be removing items from side, use a standard for loop
	* and remember that when something is moved, everything "slides over"
	*/

	public void simplify(ArrayList<Term> side){
		for(Term t:side){
			for(Term s:side){
				if(t!=s && t.getCoefficient()!=0){
					if(Term.areLikeTerms(s, t)){
						Term result = Term.combine(s, t);
						t.setCoefficient(result.getCoefficient());
						s.setCoefficient(0);
					}
					
				}
				
			}
		}
		
		for(int i=side.size()-1; i>-1;i--){
			if( side.get(i).getCoefficient()==0) side.remove(i);
		}
	}

	/**
	* 
	* @param sideWithVariable - we can assume the side with a variable is of the form ax + b
	* @return the solution
	* 
	* Example: 3x + 21 = 0
	* 1. Identify the constant term in the sideWithVariable (21)
	* 2. Identify the variable term in the sideWithVariable (3x)
	* 3. Add the additive inverse of the constant Term to both sides of the equation (3x = -21)
	* 4. Multiple both sides by the additive inverse of the coefficient of the variable term (.33333333)
	*/

	public void solveLinear(ArrayList<Term> sideWithVariable){
		// Simplify this later
		ArrayList<Term> sideWithoutVariable = sideWithVariable == leftSide ? rightSide : leftSide;
		for (Term t: sideWithVariable) {
			if (t.getExponent() == 0) {
				sideWithoutVariable.add(t.getAddInverse());
				sideWithVariable.remove(t);
				break;
			}
		}
		
		double scalar = sideWithVariable.get(0).getCoefficient();
		multiplyScalar(leftSide, scalar);
		multiplyScalar(rightSide, scalar);
	}
	
	public void solveQuadratic(ArrayList<Term> side) {
		// sort the terms
		sortSide(side);
		System.out.println(side.toString());
		
		String var = side.get(0).getVariable();
		
		// assign a, b, and c
		double[] coefs = new double[3];
		for (int i = 0; i < 3; i++) {
			coefs[i] = side.get(i).getCoefficient();
		}
		
		// discriminate
		if (coefs[1]*coefs[1] - 4*coefs[0]*coefs[2] == 0) {
			// One real root
			/* 
			 * Problems:
			 * - When the coefficient of a is more than 1 the roots are no longer just the inverse of b
			 * 
			 */
			System.out.println("This part works");
			String addSubTemp = coefs[1] > 0 ? " + " : " - ";
			// This only works if a is 1
			System.out.println("(" + Math.sqrt(coefs[0]) + var + addSubTemp + Math.sqrt(coefs[2]) + ")^2");
			System.out.println(var + " = " + -Math.sqrt(coefs[2]));
		} else if (coefs[1]*coefs[1] - 4*coefs[0]*coefs[2] > 0) {
			// Two real roots
			
		} else {
			// Can't compute
		}
	}

	/**
	* 
	* @param side
	* @param scalar
	* multiplies all Terms on the given side by the given scalar
	* (Hint: use setCoefficient(double))
	*/

	public void multiplyScalar(ArrayList<Term> side, double scalar){
		for (Term t: side)
			t.setCoefficient(t.getCoefficient() / scalar);
	}
	
	public static void sortSide(ArrayList<Term> t) {
		Collections.sort(t, new Comparator<Term>() {
			public int compare(Term a, Term b) {
				return b.getExponent() - a.getExponent();
			}
		});
	}
}