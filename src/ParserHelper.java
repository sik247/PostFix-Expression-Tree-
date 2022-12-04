import java.util.ArrayList;
import java.util.List;

public class ParserHelper {

	    private static List<String> parse(char[] input) {
		    List<String> parsed = new ArrayList<String>(); //create object ***WAS PRIVATE 
		    for (int i = 0; i < input.length; ++i) { // i = 1 4
		        char c = input[i]; //input [1]
		        if (Character.isDigit(c)) { //check if digit
		        
		            String number = input[i] + ""; // if digit add to String number 
		            for (int j = i + 1; j < input.length; ++j) { // j =  2 then j = 3 
		                if (Character.isDigit(input[j])) { // j = number
		                    number += input[j]; //add to String number 
		                    i = j; //3 
		                } else {
		                    break;
		                }
		            }
		            parsed.add(number);
		        } else if (c == '*' || c == '/' || 
		                   c == '+' || c == '^' || 
		                   c == '-' || c == '(' || c == ')') {
		            parsed.add(c + "");
		        }
		    }
		    return parsed;
		}
	}