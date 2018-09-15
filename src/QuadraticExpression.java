/**
 * The QuadraticExpression application prints the number of roots found
 * in the expression and the value of the expression at x
 * @author Juan Martinez (mart1316@pnw.edu)
 */

public class QuadraticExpression implements Cloneable {
    private double a;
    private double b;
    private double c;


    /**
     * Default constructor initializes all instance variables as 0
     */
    public QuadraticExpression() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Constructor initializes instance variables with pre-defined values
     * @param a value of a
     * @param b value of b
     * @param c value of c
     */
    public QuadraticExpression(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method returns the values of the expression as a string
     * @return returns expression as a string
     */
    public String toString() {
        String expression = "";

        if (a == 0 && b == 0 && c == 0)
            return "0";

        if (a != 0) {
            expression += a + "x^2";
        }

        if (b != 0 && a != 0) {
            expression += " + " + b + "x";
        } else if (b != 0) {
            expression += b + "x";
        }

        if ( (a != 0 || b != 0) && c != 0) {
            expression += " + " + c;
        } else expression += c;
        return expression;
    }

    /**
     * Method that returns the value of the expression at x
     * @param x value to be evaluated
     * @return returns the value after computation
     */
    public double evaluate(double x) {
        return (a * x * x) + (b * x) + c;
    }

    /**
     *Access modifier
     * @param a parameter value initiates variable a
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * Access modifier
     * @param b parameter value initiates variable b
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * Access modifier
     * @param c parameter value initiates variable c
     */
    public void setC(double c) {
        this.c = c;
    }

    /**
     * static method adds the values of two objects, creates a new object and initialises the new objects with the sums
     * @param q1 object to be evaluated
     * @param q2 object to be evaluated
     * @return returns new object initialized with the sum from two other objects
     */
    public static QuadraticExpression sum(QuadraticExpression q1, QuadraticExpression q2) {
        double aSum = q1.a + q2.a;
        double bSum = q1.b + q2.b;
        double cSum = q1.c + q2.c;
        QuadraticExpression sum = new QuadraticExpression(aSum, bSum, cSum);
        return sum;
    }

    /**
     * static method creates a new object and initializes the object with scaled values from another object
     * @param r scale parameter
     * @param q object parameter
     * @return returns new object with initialized values from a scaled object
     */
    public static QuadraticExpression scale(double r, QuadraticExpression q) {
        double aScale = r * q.a;
        double bScale = r * q.b;
        double cScale = r * q.c;

        QuadraticExpression scale = new QuadraticExpression(aScale, bScale, cScale);
        return scale;
    }

    /**
     * method identifies if quadratic expression has roots
     * @return returns root number(s)
     */
    public int numberOfRoots() {

        if (a == 0 && b == 0 && c == 0)
            return 3;

        if (a == 0) {
            if (b == 0)
                return 0;
            return 1;
        }

        if (b * b < 4 * a * c)
            return 0;

        if(b * b == 4 * a * c)
            return 1;

        return 2;
    }

    /**
     * method adds the values of another object to the calling object
     * @param q object parameter
     */
    public void add( QuadraticExpression q) {
        this.a += q.a;
        this.b += q.b;
        this.c += q.c;
    }


    /**
     * method returns smallest the value of the root(s)
     * @return returns the smallest root value
     * @throws Exception if there are no root
     */
    public double smallerRoot() throws Exception {

        if (numberOfRoots() == 3)
            return -Double.MAX_VALUE;

        if (numberOfRoots() == 0) {
            throw new Exception("no solution");
        }

        if (numberOfRoots() == 1) {
            if (a == 0)
                return -c / b;
            return -b / (2 * a);
        }

        double root1 = (-b / 2 * a) + (Math.sqrt(b * b - (4 * a * c)) / (2 * a));
        double root2 = (-b / 2 * a) - (Math.sqrt(b * b - (4 * a * c)) / (2 * a));
        return Math.min(root1,root2);
    }

    /**
     * method evaluates the larger root
     * @return returns the value of the root
     * @throws Exception trows exception if calling object has no roots
     */
    public double largerRoot() throws Exception {
        if (numberOfRoots() == 3)
            return Double.MAX_VALUE;

        if (numberOfRoots() == 0)
            throw new Exception("No solution");

        if (numberOfRoots() == 1)
            return smallerRoot();

        double root1 = (-b / 2 * a) + (Math.sqrt(b * b - (4 * a * c)) / (2 * a));
        double root2 = (-b / 2 * a) - (Math.sqrt(b * b - (4 * a * c)) / (2 * a));
        return Math.max(root1, root2);
    }

    /**
     * boolean method overrides object class and evaluates the calling object and other object
     * @param otherObj object parameter
     * @return returns true if calling object's variables and other object's variables are equal
     */
    public boolean equals(QuadraticExpression otherObj) {
        return (this.a == otherObj.a &&
                this.b == otherObj.b &&
                this.c == otherObj.c );
    }

    /**
     * method creates a clone
     * @return returns a copy of the calling object
     */
    public QuadraticExpression clone() {
        QuadraticExpression clone;

        try {
            clone = (QuadraticExpression) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }
        return clone;
    }
}