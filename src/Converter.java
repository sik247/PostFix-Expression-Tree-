// Imports
import java.util.ArrayList;
import java.util.List;

/**
 * ​The Converter class takes​ ​an​ ​infix​ ​expression​ ​that​ ​is​ ​generated​ ​by the​ ​user ​and can​ ​convert​
 * ​it​ ​to​ ​a​ ​postfix​ ​expression​ ​that​ ​can​ ​be​ ​evaluated​ ​using​ ​code.​
 * The Converter​ ​class​ ​uses​ ​a​ ​stack​ ​to​ ​accomplish​ ​this.
 */
public class Converter {

    // Stores inputted infix expression
    private String infixExpression;

    // LinkedStack object used to temporarily store operators and their order
    private LinkedStack <String>stack = new LinkedStack<String>();
    
    /**
     * Constructor
     * When​ ​the​ ​Converter​ ​class​ ​is​ ​instantiated,​ ​a​ ​String​ ​is​ ​passed​
     * ​representing​ ​the​ ​infix expression​ ​entered​ ​by​ ​the​ ​user.​
     * @param inInfixExpression Infix input
     */
    Converter(String inInfixExpression) {
        this.infixExpression = inInfixExpression;
    }

    /**
     * ​Converts ​the​ ​infix​ ​expression​ ​to​ ​a​ ​postfix​ ​expression.
     * ​The​ ​postfix expression​ will ​be​ ​a​ ​string​ ​that​ ​can​ ​be​
     * ​evaluated​ ​by​ ​the​ ​calculator.​ ​
     * Each operator​ ​and​ ​operand will ​be​ ​separated​ ​by​ ​spaces.​
     * @return the infix expression converted into a postfix expression
     */
    public String toPostFix() {

        // Empty postFix output as a StringBuilder
        StringBuilder output = new StringBuilder();

        char[] toBeParsed = this.infixExpression.toCharArray();
        List<String> parsedInfix = parse(toBeParsed);

        // Loops through the parsed list of operands and operators
        // Adds through
        for (int i = 0; i < parsedInfix.size(); i++) {
            // Current String of the parsed list we're looking at
            String element = parsedInfix.get(i);

            // If the String is an operator
            if (isOperator(element)) {

                // If the stack is empty, simply push the operator onto the stack
                if (stack.isEmpty()) {
                    stack.push(element);
                }
                // If element is a ")", pop off and adds elements to output
                // until you reach a "("
                else if (element.equals(")")) {
                    while ( !(stack.top().equals("(")) ) {
                        output.append(stack.pop() + " ");
                    }
                    stack.pop(); // Pops off the "("
                }
                // If the element is a "(" just push it onto the stack BUG FIX
                else if (element.equals("(")) {
                    stack.push(element);
                }
                // If element is not a "(" or ")" check if it has lower precedence 
                // than the top of the stack
                else {
                    // While the stack isn't empty, pop off the stack until 
                    // the top element is of higher precedence than element
                    while( (!stack.isEmpty()) &&  (!stack.top().equals("(")) && getPrecedence(element)  <= getPrecedence((String) stack.top()))  {
                        output.append(stack.pop() + " ");
                    }
                    // Push the element onto the stack (now lower precedence than the top)
                    stack.push(element);
                }

            }
            // If the String is a number
            else {

                output.append(element + " ");

            }
        }

        // After all operands and operators have been processed,
        // pop all remaining operators off of the stack. 
        while (!stack.isEmpty()) {
            output.append(stack.pop() + " ");
        }

        // Remove the extraneous space at the end of the StringBuilder
        output.deleteCharAt(output.length() - 1);
        
        // Return the output as a string
        return output.toString();

    }

    /**
     * Parses the input character array to separate full numbers and operands
     * @param input input character array to be parsed
     * @return parsed string array
     */
    private static List<String> parse(char[] input) {

	    List<String> parsed = new ArrayList<String>();
	    for (int i = 0; i < input.length; ++i) {
	        char c = input[i];
            // If the character is a digit, loop through the array until you get to the last digit of the number
            // and add the whole number to the parsed list
	        if (Character.isDigit(c)) {
	            String number = input[i] + "";
	            for (int j = i + 1; j < input.length; ++j) {
                    // If the next character in position i + 1 is a digit, add it to the element to be added
                    // to the parsed list and increment both i and j
	                if (Character.isDigit(input[j])) {
	                    number += input[j];
	                    i = j;
	                } else {
	                    break;
	                }
	            }
	            parsed.add(number);
            // If the character is an operator, add it to the parsed list
	        } else if (c == '*' || c == '/' || 
	                   c == '+' || c == '^' || 
	                   c == '-' || c == '(' || c == ')') {
	            parsed.add(c + "");
	        }
	    }
	    return parsed;

	}

    /**
     * Determines if an inputted String is an operator
     * @param input string to be assessed
     * @return boolean if the string is an operand
     */
    private boolean isOperator(String input) {

        return input.equals("*") || input.equals("/") || 
        input.equals("+") || input.equals("^") || 
        input.equals("-") || input.equals("(") ||
        input.equals(")") ;

    }

    /**
     * Returns the precedence of a given operator
     * Returns 0 if the input does not match the set operators
     * @param operator string to be assessed
     * @return the precedence of the given operator
     */
    private int getPrecedence(String operator) {

        if (isOperator(operator)) {
            switch (operator) {
                case "+":
                case "-":
                    return 0;
                case "*":
                case "/":
                    return 1;
                case "^":
                    return 2;
                case "(":
                case ")":
                    return 3;
            }
        }

        // By default return 0 if the input String is not an operator
        return 0;

    }
}
				
			
				
				
				
			
		

			
		
		