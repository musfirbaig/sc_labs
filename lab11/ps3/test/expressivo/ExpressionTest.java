/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    
    // covers spaces formats
    @Test
    public void testParseSpaces() {
        Expression p1 = Expression.parse("1+1");
        Expression p2 = Expression.parse(" 1 + 1 ");
        Expression p3 = Expression.parse(" 1+ 1");
        Expression p4 = Expression.parse("1 +1 ");
        
        assertTrue("Expected to be equal", p1.equals(p2));
        assertTrue("Expected to be equal", p1.equals(p3));
        assertTrue("Expected to be equal", p1.equals(p4));
    }
    
    // covers number - integer, fraction
    //        # number = 1
    @Test
    public void testParseNumber() {
        Expression p1 = Expression.parse("1");
        Expression p2 = Expression.parse("1.0");
        Expression p3 = Expression.parse(".1");
        
        Expression e1 = new Number(1.0);
        Expression e2 = new Number(0.1);
        
        assertTrue("Expected Expression '1'", p1.equals(e1));
        assertTrue("Expected Expression '1'", p2.equals(e1));
        assertTrue("Expected Expression '0.1'", p3.equals(e2));
    }
    
    // covers variable case sensitivity
    //        # variable = 1
    //        # number = 0
    @Test
    public void testParseVariable() {
        Expression p1 = Expression.parse("x");
        Expression p2 = Expression.parse("X");
        
        Expression e1 = new Variable("x");
        Expression e2 = new Variable("X");
        
        assertTrue("Expected Expression 'x'", p1.equals(e1));
        assertTrue("Expected Expression 'X'", p2.equals(e2));
        assertFalse("Expected not equal", p1.equals(p2));
    }
    
    // covers # add, mult = 1
    //        # number, variable > 1
    @Test
    public void testParseOneX() {
        Expression p1 = Expression.parse("1 + 1");
        Expression p2 = Expression.parse("x * x");
        Expression e1 = new Plus(new Number(1.0), new Number(1.0));
        Expression e2 = new Times(new Variable("x"), new Variable("x"));
        
        assertTrue("Expected Expression '1+1'", p1.equals(e1));
        assertTrue("Expected Expression 'x*x'", p2.equals(e2));
    }
    
    // covers # add, mult > 1
    //        grouping
    @Test
    public void testParseGrouping() {
        Expression p1 = Expression.parse("1 + (1 + 1)");
        Expression p2 = Expression.parse("(1 + 1) + 1");
        Expression e1 = new Plus(new Number(1.0), new Plus(new Number(1.0), new Number(1.0)));
        Expression e2 = new Plus(new Plus(new Number(1.0), new Number(1.0)), new Number(1.0));
        
        Expression p3 = Expression.parse("1 * (1 * 1)");
        Expression p4 = Expression.parse("(1 * 1) * 1");
        Expression e3 = new Times(new Number(1.0), new Times(new Number(1.0), new Number(1.0)));
        Expression e4 = new Times(new Times(new Number(1.0), new Number(1.0)), new Number(1.0));
        
        assertTrue("Expected grouping '1 + (1 + 1)'", p1.equals(e1));
        assertTrue("Expected grouping '(1 + 1) + 1'", p2.equals(e2));
        assertTrue("Expected grouping '1 * (1 * 1)'", p3.equals(e3));
        assertTrue("Expected grouping '(1 * 1) * 1'", p4.equals(e4));
    }
    
    // covers precedence
    @Test
    public void testParsePrecedence() {
        Expression p1 = Expression.parse("1+1*x");
        Expression e1 = new Plus(new Number(1.0), new Times(new Number(1.0), 
                                                            new Variable("x")));
        
        assertTrue("Expected grouping '1 + (1 * 1)'", p1.equals(e1));
    }
    
    // covers invalid input - empty string
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidEmpty() {
        Expression.parse("");
    }
    
    // covers invalid input - negative number
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidNegative() {
        Expression.parse("-1");
    }
    
    // covers invalid input - space between variables 
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidSpace() {
        Expression.parse("a b");
    }
    
    // covers invalid input - no operation
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidNoOperation() {
        Expression.parse("3x");
    }
    
    // covers invalid input - incomplete operation
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidIncompleteOperation() {
        Expression.parse("3 *");
    }
    
    // covers invalid input - incomplete parenthesis
    @Test(expected=IllegalArgumentException.class)
    public void testParseInvalidIncompleteParanthesis() {
        Expression.parse("(3");
    }
    
    
    // tests for differentiate()
    
    // covers Number = 1
    @Test
    public void testDifferentiateNumber() {
        Expression e = new Number(1.0);
        Expression d = new Number(0.0);
        
        assertEquals("Expected derivative 0", e.differentiate("x"), d);
    }
    
    // covers Variable = 1
    //        diff var = present/not present
    @Test
    public void testDifferentiateVariable() {
        Expression e1 = new Variable("x");
        Expression e2 = new Variable("y");
        Expression d1 = new Number(1.0);
        Expression d2 = new Number(0.0);
        
        assertEquals("Expected derivative 0", e1.differentiate("x"), d1);
        assertEquals("Expected derivative 1", e2.differentiate("x"), d2);
    }
    
    // covers Plus
    //        Number >1
    @Test
    public void testDifferentiatePlus() {
        Expression e = new Plus(new Number(1.0), new Number(1.0));
        Expression d = new Plus(new Number(0.0), new Number(0.0));
        
        assertEquals("Expected derivative 0.0+0.0", e.differentiate("x"), d);
    }
    
    // covers Times
    //        Variable >1
    @Test
    public void testDifferentiateTimes() {
        Expression e = new Times(new Variable("x"), new Variable("x"));
        Expression d = new Plus(new Times(new Variable("x"), new Number(1.0)),
                                new Times(new Variable("x"), new Number(1.0)));
        
        assertEquals("Expected derivative x*1+x*1", e.differentiate("x"), d);
    }
    
    


}
