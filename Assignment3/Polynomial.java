/*****************************************************************************
 *  Polynomial class creates a representation of polynomials using linked list
 *  Assignment: 3, option 2
 * @author <a href="mailto:mart1316@pnw.edu">Juan M.</a>
 * ****************************************************************************/

public class Polynomial {
    private Node head;

    /**
     * constructor creates a polynomial object with coefficient = 0 in the x^0 term
     * */
    public Polynomial() {
        this(0.0);
    }

    /**
     * Creates a polynomial with a user specified coefficient element in the x^0 term
     * @param a0
     * The coefficient element
     * */
    public Polynomial(double a0) {
        head = new Node(a0, 0, null);
    }

    /**
     *Creates a copy of an existing polynomial
     * @param p
     * the polynomial which is to be copied
     * */
    public Polynomial(Polynomial p) {
        this(0);
        for (Node cursor = p.head; cursor!= null; cursor = cursor.link) {
            this.add_to_coef(cursor.coefficient, cursor.exponent);
        }
    }

    /**Adds coefficient and exponent terms to the polynomial collection in decreasing order of degree
     * If the term added results in a coefficient of zero, then that term is deleted from the polynomial collection
     * @param amount amount to be added to the coefficient
     * @param newExponent the degree of the term whose coefficient is to be modified
     * */
    @SuppressWarnings("LoopConditionNotUpdatedInsideLoop")
    public void add_to_coef(double amount, int newExponent) {
        //if amount == 0, no changes are made
        if (amount == 0) {
            return;
        }

        //if  exponent is greater than the leading exponents in the collection, it is added to the
        // front of the collection
        if (newExponent > head.exponent) {
            head = new Node(amount, newExponent, head);
            return;
        }

        //if coefficient == 0 for the leading term, the leading term is then removed from the collection
        if ((head.exponent == newExponent) && (amount + head.coefficient == 0)) {
            head = head.link;
            return;
        }

        //loop to transverse polynomial
        for (Node cursor = head; cursor != null; cursor = cursor.link) {
            //if target exponent is in the collection, the conditions checks for coefficient resulting in zero
            if (cursor.exponent == newExponent) {
                //if coefficient is zero that term is removed from the collection
                if (cursor.coefficient + amount == 0) {
                    for (Node cursor2 = head; newExponent != 0; cursor2 = cursor2.link) {
                        if (cursor2.link.exponent == newExponent) {
                            cursor2.link = cursor2.link.link;
                            return;
                        }
                    }
                }
                //if coefficient result is not zero, the term is added
                cursor.coefficient += amount;
                return;
            }

            //if the target exponent is not in the collection, it is added  between greater and lesser exponent terms
            if (cursor.exponent > newExponent && newExponent > cursor.link.exponent) {
                cursor.link = new Node(amount, newExponent, cursor.link);
                return;
            }
        }//end of loop
    }

    /**
     * Adds the terms of p into the activating polynomial
     * @return Returns a Polynomial that is the sum of p and this Polynomial.
     * @param p The Polynomial to be added to the activating Polynomial.
     * **/
    public Polynomial add(Polynomial p) {
        for (Node cursor = p.head; cursor != null; cursor = cursor.link) {
            this.add_to_coef(cursor.coefficient, cursor.exponent);
        }
        return this;
    }

    /**
     * creates and returns a polynomial obtained by multiplying the terms of p and the colling polynomial
     * @param p The polynomial to be multiplied
     * @return The product of the activating Polynomial and p
     * */
    public Polynomial multiply(Polynomial p) {
        Polynomial multiPoly = new Polynomial(0);

        for (Node i = p.head; i != null; i = i.link ) {
             for (Node j = this.head; j != null; j = j.link) {
                 double coefficient = i.coefficient * j.coefficient;
                 int exponent = i.exponent + j.exponent;
                 multiPoly.add_to_coef(coefficient, exponent);
             }
         }

        return multiPoly;
    }

    /**
     *Sets the coefficient of a specified term to a specified value
     * @param coefficient The new value of the coefficient
     * @param exponent The degree of the term whose coefficient is to be modified
     * */
    public void assign_coef(double coefficient, int exponent) {
        //if exponent > than collection exponents, the term is added in the front of the leading terms;
        if (exponent > head.exponent) {
            add_to_coef(coefficient, exponent);
            return;
        }
        //if coefficient == 0, the term is removed except for exponent x^0
        if (coefficient == 0) {
            if (head.exponent == exponent) {
                head = head.link;
                return;
            }
            for (Node cursor = head; true; cursor = cursor.link) {
                if (cursor.link.exponent == exponent) {
                    if (cursor.link.exponent == 0) {
                        cursor.link.coefficient = 0;
                        return;
                    }
                    cursor.link = cursor.link.link;
                    return;
                }
            }
        }
        //if exponent is in the collection, the coefficient is assign
        for (Node cursor = head; cursor != null; cursor = cursor.link) {
            if (cursor.exponent == exponent) {
                cursor.coefficient = coefficient;
                return;
            }
        }
        //if all other conditions fail, the term is then added to the collection
        add_to_coef(coefficient, exponent);
    }

/**
 * Returns coefficient at specified exponent of this polynomial
 * @return The coefficient of the term
 * @param exponent The exponent of the term whose coefficient is sought
 * @throws IllegalArgumentException
 *  throws exception if given exponent is greater than the polynomial terms
 **/
    public double coefficient(int exponent) throws IllegalArgumentException {
        if (exponent > head.exponent) {
            throw new IllegalArgumentException("exponent argument is greater than polynomial terms");
        }

        for (Node cursor = head; cursor != null; cursor = cursor.link) {
            if (cursor.exponent == exponent) {
                return cursor.coefficient;
            }
            if (cursor.exponent < exponent) {
                return 0.0;
            }
        }
        return 0.0;
    }

    /**
     * evaluates polynomial at the given value of x
     * @return The value of this Polynomial with the given value for the variable x.
     * @param x The value at which the Polynomial is to be evaluated.
     * evaluates terms by using Horner's method
     ***/
    public double eval(double x) {
        Node cursor = head;
        double result = cursor.coefficient;
        int degree = cursor.exponent;

        while(degree != 0) {
            result *= x;
            degree--;
            if (degree == cursor.link.exponent) {
                cursor = cursor.link;
                result += cursor.coefficient;
            }
        }
        return result;
    }

    /**
     * creates a string representing the polynomial expression with coefficients displayed to the tenths place,
     * omitting any coefficients that are zero. If all coefficients are 0, then the zero function is reported.
     * @return string representation of polynomial
     **/
    @Override
    public String toString() {
        //if coefficient is zero and there are no other terms, the output is 0
        if (head.coefficient == 0 && head.link == null) {
            return "0";
        }

        StringBuilder str = new StringBuilder();
        for (Node cursor = head; cursor != null; cursor = cursor.link) {
            //coefficient is rounded to the tenths place
            cursor.coefficient = Math.round( cursor.coefficient * 10) / 10.0;

            //conditional statements to properly format the string output
            if (cursor.coefficient > 0) {
                str.append(" + ");
                if (cursor.coefficient == 1) {
                    if (cursor.exponent == 0) {
                        str.append(cursor.coefficient);
                        continue;
                    }
                    str.append("x");
                } else {
                    if (cursor.exponent == 0) {
                        str.append(cursor.coefficient);
                        continue;
                    }
                    str.append(cursor.coefficient).append("x");
                }
                if (cursor.exponent == 0) {
                    continue;
                }
                if (cursor.exponent != 1) {
                    str.append("^").append(cursor.exponent);
                }
            } else if (cursor.coefficient < 0) {
                str.append(" - ");
                if (Math.abs(cursor.coefficient) == 1) {
                    if (cursor.exponent == 0) {
                        str.append(Math.abs(cursor.coefficient));
                        continue;
                    }
                    str.append("x");
                } else {
                    str.append(Math.abs(cursor.coefficient)).append("x");
                }
                if (cursor.exponent == 0) {
                    continue;
                }
                if (cursor.exponent != 1) {
                    str.append("^").append(cursor.exponent);
                }
            }
        }
        //conditional statements removes leading + or - sign
        if (str.charAt(1) == '+') {
            str.delete(0, 3);
        } else if (str.charAt(1) == '-') {
            str.delete(2,3);
        }
        return str.toString().trim();
    }

    /**
     * Node inner-class
     * */
    private static class Node {
        private double coefficient;
        private int exponent;
        private Node link;

        private Node(double coefficient, int exponent, Node link) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.link = link;
        }
    }
}