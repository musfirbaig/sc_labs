/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //
    // Commands.differentiate():
    //      # numbers = 1, > 1
    //      # variables = 1, >1
    //      Differentiation variable - present, not present
    //      add
    //      mult
    //      exception for illegal input
    //
    // Commands.simplify():
    //      # numbers = 1, > 1
    //      # variables = 1, >1
    //      # environment var = 0, 1, >1
    //      variable in env - present, not present
    //      add
    //      mult
    //      exception for illegal input

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // tests for Commands.differentiate()
    
    // covers exception
    @Test(expected=IllegalArgumentException.class)
    public void testDifferentiateException() {
        Commands.differentiate("(3", "x");
    }
    
    // covers number = 1
    @Test
    public void testDifferentiateNumber() {
        
        assertEquals("Expected string 0.0", Commands.differentiate("2", "x") , "0.0");
    }
    
    // covers variable = 1
    //        diff var - present/not present
    @Test
    public void testDifferentiateVariable() {
        
        assertEquals("Expected string 1.0", Commands.differentiate("y", "y") , "1.0");
        assertEquals("Expected string 0.0", Commands.differentiate("x", "y") , "0.0");
    }

    //covers add
    //       variable >1
    @Test
    public void testDifferentiateAdd() {
        
        assertEquals("Expected String (1.0 + 1.0)", 
                     Commands.differentiate("x+x", "x"), "(1.0 + 1.0)");
    }
    
    //covers mult
    //       number >1
    @Test
    public void testDifferentiateMult() {
        
        assertEquals("Expected String ((1.0 * 0.0) + (2.0 * 0.0))", 
                      Commands.differentiate("1*2", "x"), "((1.0 * 0.0) + (2.0 * 0.0))");
    }

    
}
