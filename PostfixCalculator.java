import java.util.Stack;

public class PostfixCalculator {
    // Function to evaluate a postfix expression
    public int evaluatePostfix(String postfixExpression) {
        // Create a stack to store operands
        Stack<Integer> opStack = new Stack<>();

        // Iterate through each character in the postfix expression
        for (char c : postfixExpression.toCharArray()) {
            // Check if the character is a digit
            if (Character.isDigit(c)) {
                // Convert the digit character to an integer in base 10 and push it onto the operand stack
                opStack.push(Character.digit(c, 10));
            } else {
                // If the character is not a digit, it must be an operator
                // Check if there are at least two operands on the stack, otherwise throw exception
                if (opStack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }

                // Pop the top two operands from the stack and store in variables
                int operand2 = opStack.pop();
                int operand1 = opStack.pop();

                // Perform the operation based on the current operator
                switch (c) {
                    case '+':
                        opStack.push(operand1 + operand2);
                        break;
                    case '-':
                        opStack.push(operand1 - operand2);
                        break;
                    case '*':
                        opStack.push(operand1 * operand2);
                        break;
                    case '/':
                    case '%':
                        // Check for division by zero
                        if (operand2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        if (c == '/') {
                            opStack.push(operand1 / operand2);
                        } else {
                            opStack.push(operand1 % operand2);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + c);
                }
            }
        }

        // After processing all characters, there should be exactly one value on the stack, the result
        if (opStack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }

        // Pop and return the final result from the stack
        return opStack.pop();
    }

    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator();

        // Example 1: Valid Expression
        String expression1 = "42*3+";
        System.out.println("Result 1: " + calculator.evaluatePostfix(expression1));

        // Example 2: Valid Expression
        String expression2 = "53+7*";
        System.out.println("Result 2: " + calculator.evaluatePostfix(expression2));

        // Example 3: Invalid Expression
        try {
            String expression3 = "42*+"; // Missing operand
            System.out.println("Result 3: " + calculator.evaluatePostfix(expression3));
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
        // Example 4: Valid Expression
        String expression4 = "123*+";
        System.out.println("Result 4: " + calculator.evaluatePostfix(expression4));
        
        // Example 5: Valid Expression
        String expression5 = "32/4*";
        System.out.println("Result 5: " + calculator.evaluatePostfix(expression5));
        
        // Example 6: Valid Expression
        String expression6 = "87%2+";
        System.out.println("Result 6: " + calculator.evaluatePostfix(expression6));
        
    }
}
