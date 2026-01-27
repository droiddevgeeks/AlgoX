package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement: Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 * <p>
 * Note that operands in the returned expressions should not contain leading zeros.
 * <p>
 * Note that a number can contain multiple digits.
 * Example 1:Input:num = "123", target = 6
 * Output:["1*2*3","1+2+3"]
 * Explanation:Both "1*2*3" and "1+2+3" evaluate to 6.
 * <p>
 * Example 2:Input:num = "232", target = 8
 * Output:["2*3+2","2+3*2"]
 * Explanation:Both "2*3+2" and "2+3*2" evaluate to 8.
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        //String num = "123";
        String num = "232";
        int target = 8;
        List<String> result = new ArrayList<>();
        evaluateExpression(result, num, target, 0, 0, 0, new StringBuilder());
        for (String temp : result) {
            System.out.println(temp);
        }
    }

    private static void evaluateExpression(List<String> result,
                                           String num,
                                           int target,
                                           int index,
                                           long currValue,
                                           long lastOperand,
                                           StringBuilder expression) {

        if (num.length() == index) {
            if (currValue == target) {
                result.add(expression.toString());
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // for number not starting with 0
            if (i != index && num.charAt(index) == '0') {
                break;
            }

            long currNumber = Long.parseLong(num.substring(index, i + 1));
            int lastLength = expression.length();

            if (index == 0) {
                // First number (no operator)
                expression.append(currNumber);
                evaluateExpression(result, num, target, i + 1, currNumber, currNumber, expression);
                expression.setLength(lastLength);
            } else {

                expression.append('+').append(currNumber);
                evaluateExpression(result, num, target, i + 1, currValue + currNumber, currNumber, expression);
                expression.setLength(lastLength);

                expression.append('-').append(currNumber);
                evaluateExpression(result, num, target, i + 1, currValue - currNumber, -currNumber, expression);
                expression.setLength(lastLength);

                expression.append('*').append(currNumber);
                evaluateExpression(result, num, target, i + 1, currValue - lastOperand + (lastOperand * currNumber), lastOperand * currNumber, expression);
                expression.setLength(lastLength);
            }
        }
    }
}
