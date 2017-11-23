
import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;
	
	public static void main(String args[]){

		
	}
	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();
		
		//..
		for(int i=0; i<expressionString.length(); i++){
			token.append(expressionString.charAt(i));
		}
		for(int i=0; i<token.length(); i++){
			//if the token is a number
			if(token.charAt(i) <= 57 && token.charAt(i) >= 48){
				int numBig = 0;
				int index = i;
				while((token.charAt(index) <= 57 && token.charAt(index) >= 48)){
					numBig++;
					index++;
					
				}
				String s = "";
				for(int k=0; k<numBig; k++){
					s = s + expressionString.charAt(i+k);
				}
				tokenList.add(s);
				i+= numBig-1;
			}
			//else if the token is a + or a -
			else if(token.charAt(i)==43 || token.charAt(i)==45){
				//check if its decrement or increment
				if(token.charAt(i+1)==43 || token.charAt(i+1)==45){
					tokenList.add("" + expressionString.charAt(i)+ "" + expressionString.charAt(i+1));
					i++;
				} else {
					tokenList.add("" + expressionString.charAt(i));
				}
			}
			//if its a blank space continue
			else if(token.charAt(i)==32){
				continue;
				
				
			}
			//else is other character
			else{
				tokenList.add(""+expressionString.charAt(i));
			}
		}
		System.out.println(tokenList);
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();
		
		//..

		for(int expIndex=0; expIndex<tokenList.size();){
			for(int k=expIndex; !(tokenList.get(k).equals(")") || tokenList.get(k).equals("]"));expIndex++,k++){
				if(tokenList.get(k).equals("(")){
					continue;
				}
				else if(isInteger(tokenList.get(k))){
					valueStack.push(Integer.parseInt(tokenList.get(k)));
				}else{
					operatorStack.push(tokenList.get(k));
				}
			}
			if(operatorStack.isEmpty() || valueStack.isEmpty()){
				continue;
			}
			if(operatorStack.peek().equals("+")){
				valueStack.push(valueStack.pop()+valueStack.pop());
				operatorStack.pop();
			}else if(operatorStack.peek().equals("-")){
				int first =valueStack.pop();
				int second=valueStack.pop();
				valueStack.push(second-first);
				operatorStack.pop();
			}else if(operatorStack.peek().equals("/")){
				int first =valueStack.pop();
				int second=valueStack.pop();
				valueStack.push(second/first);
				operatorStack.pop();
			}else if(operatorStack.peek().equals("*")){
				valueStack.push(valueStack.pop()*valueStack.pop());
				operatorStack.pop();
			}else if(operatorStack.peek().equals("[")){
				valueStack.push(Math.abs(valueStack.pop()));
				operatorStack.pop();
			}else if(operatorStack.peek().equals("++")){
				valueStack.push((valueStack.pop())+1);
				operatorStack.pop();
			}else if(operatorStack.peek().equals("--")){
				valueStack.push((valueStack.pop())-1);
				operatorStack.pop();
			}
			expIndex++;
		}

		//..
		return valueStack.peek();
	}

	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.  
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

