//Main to test Polynomial class
public class Main {
    public static void main(String[] args) {

        Polynomial p1 = new Polynomial();
        p1.add_to_coef(2, 1);
        p1.add_to_coef(2, 0);
        p1.add_to_coef(2, 2);
        p1.add_to_coef(1, 1);
        p1.add_to_coef(2, 0);

        Polynomial p2 = new Polynomial(7);
        p2.assign_coef(5, 2);
        p2.assign_coef(-1, 1);


        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        Polynomial multiPoly = p1.multiply(p2);
        System.out.println("p1 * p2 = " + multiPoly);
        System.out.println();

        Polynomial copyPoly = multiPoly.clone();
        System.out.println("multiPoly and copyPoly are same: " + (multiPoly == copyPoly));
        multiPoly.add_to_coef(1, 4);
        System.out.println("copyPoly: " + copyPoly);
        System.out.println("multiPoly: " + multiPoly);
        System.out.println();

        System.out.println("p1 = f(x) = " + p1);
        System.out.println("p1 = f(2) = " + p1.evaluate(2));
        System.out.println("multiPoly = f(x) = " + multiPoly);
        System.out.println("multiPoly = f(2) = " + multiPoly.evaluate(2));
        System.out.println();

        Polynomial sumPoly = p1.add(multiPoly);
        System.out.println("p1 + multiPoly = " + sumPoly);

        System.out.println("sumPoly coefficient value at x^2: " + sumPoly.coefficient(2));

    }
}
