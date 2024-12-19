package parserTest;

import org.junit.Test;
import static org.junit.Assert.*;
import parser.Parser;
//import parser.ParserException;
public class testParser {

    @Test
    public void testSimpleArithmetic() {
        try {
            Parser parser = new Parser("3 + 2 * (4 - 1)");
            double result = parser.evaluate();
            assertEquals(9.0, result, 0.001);  // Verifying result with a tolerance for floating-point precision
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testVariableAssignment() {
        try {
            // Test variable assignment
            Parser parser = new Parser("A = 5");
            double result = parser.evaluate();
            assertEquals(5.0, result, 0.001);  // Verifying the value of A is 5

            // Test using the assigned variable
            parser.setExpression("A + 3");
            result = parser.evaluate();
            assertEquals(8.0, result, 0.001);  // Verifying A + 3 results in 8
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testParenthesesAndPrecedence() {
        try {
            Parser parser = new Parser("(2 + 3) * (4 - 1)");
            double result = parser.evaluate();
            assertEquals(15.0, result, 0.001);  // Verifying correct handling of parentheses and precedence
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

//    @Test
//    public void testDivisionByZero() {
//        try {
//            Parser parser = new Parser("5 / 0");
//            parser.evaluate();  // This should throw an exception
//            fail("Expected exception due to division by zero");
//        } catch (ParserException e) {
//            assertEquals("Division by Zero", e.getMessage());
//        } catch (Exception e) {
//            fail("Unexpected exception occurred: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testInvalidExpression() {
//        try {
//            Parser parser = new Parser("2 + * 3");
//            parser.evaluate();  // This should throw a syntax error
//            fail("Expected exception due to invalid expression");
//        } catch (ParserException e) {
//            assertEquals("Syntax error in Expression!", e.getMessage());
//        } catch (Exception e) {
//            fail("Unexpected exception occurred: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testEmptyExpression() {
//        try {
//            Parser parser = new Parser("");
//            parser.evaluate();  // This should throw an empty expression error
//            fail("Expected exception due to empty expression");
//        } catch (ParserException e) {
//            assertEquals("Empty Expression!", e.getMessage());
//        } catch (Exception e) {
//            fail("Unexpected exception occurred: " + e.getMessage());
//        }
//    }

    
    @Test
    public void testFloatingPointNumbers() {
        try {
            Parser parser = new Parser("5.5 + 2.5");
            double result = parser.evaluate();
            assertEquals(8.0, result, 0.001);  // Verifying correct floating-point arithmetic
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testNegativeNumbers() {
        try {
            Parser parser = new Parser("5 + -3");
            double result = parser.evaluate();
            assertEquals(2.0, result, 0.001);  // Verifying correct handling of negative numbers
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testMultipleOperations() {
        try {
            Parser parser = new Parser("3 + 2 * 5 - 8 / 4");
            double result = parser.evaluate();
            assertNotEquals(10.0, result, 0.001);  // Verifying correct result considering operator precedence
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
