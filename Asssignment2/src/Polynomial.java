/**
 * Polynomial program
 * @author Juan J Martinez
 * Email: mart1316@pnw.edu
 * Assignment 2: Polynomial
 */

import java.util.Arrays;

public class Polynomial implements Cloneable {
    private double[] polynomial;

    /**
     * Creates a polynomial representing 0
     * */
    public Polynomial() {
        this(0);
    }

    /**
     * Creates a polynomial has a single x^0 term with coefficient a0
     * @param a0 Coefficient at x^0
     * */
    public Polynomial(double a0) {
        polynomial = new double[]{a0};
    }


    /**
     * Creates a copy of an existing Polynomial object
     * @param source A Polynomial object
     * @postcondition - Creates a copy of an existing Polynomial
     * */
    public Polynomial(Polynomial source) {
        polynomial = source.polynomial.clone();
    }

    /**
     * Adds coefficient of the specified exponent
     * @param amount Amount to add
     * @param exponent The value of the exponent
     * @precondition An amount and/or exponent to add to a Polynomial
     * @postcondition amount and exponent added to a Polynomial
     * */
    public void add_to_coef(double amount, int exponent) {
        if (exponent > this.polynomial.length - 1) {
            double[] copyPoly = Arrays.copyOf(polynomial, exponent + 1);
            copyPoly[exponent] = amount;
            polynomial = copyPoly;
        } else {
            polynomial[exponent] += amount;
        }
    }

    /**
     * Assigns coefficient and exponent value to a Polynomial
     * @precondition Coefficient and exponential values to add to a polyminial
     * @postcondition coefficient and exponent values are assigned to the Polynomial
     * @param coefficient coefficient to assign to the Polynomial
     * @param exponent exponent to assign to the Polynomial
     * */
    public void assign_coef(double coefficient, int exponent) {
        if (exponent > this.polynomial.length - 1) {
            double[] copyPoly = Arrays.copyOf(polynomial, exponent + 1);
            copyPoly[exponent] = coefficient;
            polynomial = copyPoly;
        } else {
            polynomial[exponent] = coefficient;
        }
    }

    /**
     * @postcondition Returns coefficient at specified exponent
     * @return coefficient
     * @param exponent exponent.
     * */
    public double coefficient(int exponent) {
        if (exponent > this.polynomial.length - 1) {
            return 0;
        }
        return polynomial[exponent];
    }

    /**
     * @postcondition Returns the value of this polynomial with the given value for the variable x
     * @param x the value assigned to x
     * @return returns the value of the polynomial
     *
     * */
    public double evaluate(double x) {
        double product = 0.0;
        for (int i = polynomial.length - 1; i >= 0; i--) {
            product = polynomial[i] + product * x;
        }
        return product;
    }

    /**
     * @postcondition displays the polynomial represented as a string with zero values omitted unless the polynomial equals to zero
     * */
    @Override
    public String toString() {
        if (polynomial.length == 1 && polynomial[0] == 0) {
            return "0";
        }
        String str = "";

        for (int i = 0; i < polynomial.length; i++) {

            if (polynomial[i] != 0) {
                str += polynomial[i];
                if (i != 0) {
                    if (i != 1) {
                        str += "x^" + i;
                    } else {
                        str += "x";
                    }
                }
                if (i != polynomial.length - 1) {
                    str += " + ";
                }
            }
        }

        if (str.charAt(str.length() - 2) == '+') {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    /**
     * @postcondition creates a copy of a polynomial
     * */
    public Polynomial clone() {
        Polynomial clone;
        try {
            clone = (Polynomial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not support cloneable");
        }
        clone.polynomial = polynomial.clone();
        return clone;
    }

    /**
     * @precondition two valid polynomials to add together to a new Polynomial
     * @postcondition return a new Polynomial with the added terms of two other Polynomials
     * @param otherObj Polynomial and calling polynomial
     * @return the product of two Polynomials added together
     * */

    public Polynomial add(Polynomial otherObj) {
        Polynomial sumProduct = new Polynomial();
        int polyLen = polynomial.length;
        int objLen = otherObj.polynomial.length;

        if (polyLen > objLen) {
            sumProduct.polynomial = Arrays.copyOf(polynomial, polyLen);
            for (int i = 0; i < objLen; i++) {
                sumProduct.polynomial[i] += otherObj.polynomial[i];
            }
        } else {
            sumProduct.polynomial = Arrays.copyOf(otherObj.polynomial, objLen);
            for (int i = 0; i < polyLen; i++) {
                sumProduct.polynomial[i] += polynomial[i];
            }
        }
        return sumProduct;
    }

    /**
     * @precondition Two valid Polynomial to multiply together
     * @postcondition creates new Polynomial product
     * @param p Polynomial to multiply
     * @param - Calling Polynomial to multiply
     * @return returns a Polynomial with product of two other Polynomials
     * */
    public Polynomial multiply(Polynomial p) {
        int len = polynomial.length + p.polynomial.length - 2;
        Polynomial multiPoly = new Polynomial();
        multiPoly.assign_coef(0, len);

        for (int i = 0; i < polynomial.length; i++) {
            for (int j = 0; j < p.polynomial.length; j++) {
                multiPoly.polynomial[i + j] += polynomial[i] * p.polynomial[j];
            }
        }
        return multiPoly;
    }
}