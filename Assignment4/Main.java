import java.util.Stack;

/**
 * This program takes a infix expression and converts it to a postfix expression
 * Assignment: 4
 * @author
 */
public class Main {
    public static void main(String[] args) {
        String str1 = "( ( ( A + 7 ) * ( B / C ) ) - ( 2 * D ) )";
        String str2 = "( 1.5050 + ( ( 2.5 + 3.5 ) * ( 4.5 * 5.5 ) ) )";
        System.out.println("Postfix: " + convertToPostfix(str1));
        System.out.println("Postfix: " + convertToPostfix(str2));
    }

    /**
     * this method is used to convert an infix expression to a postfix expression
     * @param str String representation of an infix expression
     * @return String representation of a postfix expression
     * @throws ArithmeticException throws arithmetic exception if a parenthesis is misplaced
     */
    private static String convertToPostfix(String str) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();


        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);

            //if c is a digit, an algebraic variable, a dot, or blank (String formatting helper) it is added to
            //the postfix expression
            if ( Character.isLetterOrDigit(c) || c == '.' || c == ' ' ) {
                postfix.append(c);

                //if c is a left parenthesis it is added to the stack and a blank (string format helper) is appended
                //to postfix expression.
            } else if ( c == '(' ) {
                stack.push(c);
                postfix.append(" ");

                //if c is an operator the stack is pop and added to the postfix expression only if c's precedence is
                // smaller or equal to the topmost operator in the stack. Otherwise, c is just added to the stack
            } else if ( isOperator(c) ) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()) ) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(c);

                //if c is a right parenthesis and the stack is not empty, the stack is then popped and added to the
                //postfix expression until a left parenthesis is encountered. If there is no left parenthesis then an
                //exception is thrown with a misplaced parenthesis statement
            } else if ( c == ')') {
                boolean hasLeftParenthesis = false;
                while ( !stack.isEmpty() ) {
                    char popped = stack.pop();
                    if ( popped == '(' ) {
                        hasLeftParenthesis = true;
                        break;
                    } else {
                        postfix.append(popped).append(" ");
                    }
                }//end of inner-loop
                if ( !hasLeftParenthesis ) {
                    throw new ArithmeticException("Misplaced parenthesis");
                }
            }
        }//end of for-loop

        //If stack is not empty, the stack is then popped and added to the postfix expression. If a left parenthesis is
        //found then an exception is thrown with a misplaced parenthesis statement;
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new ArithmeticException("Misplaced parenthesis");
            }
            postfix.append(stack.pop()).append(" ");
        }

        //String is trimmed, double spaces are replaced by single spaces, and returned
        return postfix.toString().trim().replaceAll("  ", " ");
    }

    /**
     *this method is used to identify the precedence value of an operator during an arithmetic evaluation
     * @param c character representation of a operator symbol
     * @return integer value of arithmetic precedence
     */
    private static int precedence(char c) {
        if (c == '-' || c == '+') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        }
        return -1;
    }

    /**
     * This method is used to identify a character as a operator symbol
     * @param c character representation of a operator symbol
     * @return boolean value if the character matches an operator symbol
     */
    private static boolean isOperator(char c) {
        return  c == '+' ||
                c == '-' ||
                c == '*' ||
                c == '/';
    }
}
