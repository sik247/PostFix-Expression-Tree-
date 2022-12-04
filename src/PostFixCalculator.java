import java.util.*;
public class PostFixCalculator {
	private String post = ""; 
	private LinkedStack<Double> evalStack  = new LinkedStack<Double>();
	
	PostFixCalculator  (String post ) { 
		this.post = post ; 
		
	}
	//Calcaultor takes in postfix and calculates the expression
	public double eval() { 
		String [] pfExpression = post.split(" "); 
		for (int i =0; i <pfExpression.length; i++) { 
			try { 
			int isInteger = Integer.parseInt(pfExpression[i]); 
			evalStack.push((double) Integer.parseInt(pfExpression[i]));
			
			
			} catch (Exception e) { 
			double operandOne = evalStack.pop();
			double operandTwo = evalStack.pop();
			double value = 0 ; 
			
			
			
			switch (pfExpression[i]) { 
			case "+" :
				value = operandOne + operandTwo ; 
				break;
			case "-" :
				value = operandTwo - operandOne ;
				break;
				
			case "/" :
				value = operandTwo / operandOne ;
				break;
				
			case "*" :
				value = operandOne * operandTwo ;
				break;
			case "^" :
				value = Math.pow(operandOne, operandTwo) ;
				break;
				
			
			}
			evalStack.push(value); 
			}
			
		}
		return Math.round(evalStack.pop()*10.0)/10.0; 
	}
	public static void main(String [] args) { 
		Scanner input = new Scanner(System.in); 
		System.out.println("type your infix expression "); 
		String infix = input.nextLine(); 
		Converter c = new Converter(infix) ; 
		String postfix = c.toPostFix(); 
		PostFixCalculator calc  = new PostFixCalculator(postfix) ; 
		System.out.println("converted to postfix " + postfix); 
		System.out.println("answer is " + calc.eval()); 
		
		
			
	}
	
}
	
	
