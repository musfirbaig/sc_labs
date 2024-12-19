package sumTest;

import org.junit.Test;
import static org.junit.Assert.*;
import sum.SumOfDigits;

public class sumTest {

    // Test with a regular number
    @Test
    public void testSumOfDigitsPositiveNumber() {
        long result = SumOfDigits.sumOfDigits(12345);
        assertEquals(15, result);  // 1 + 2 + 3 + 4 + 5 = 15
    }

    // Test with a negative number (should be converted to positive)
    @Test
    public void testSumOfDigitsNegativeNumber() {
    	long result = SumOfDigits.sumOfDigits(-12345);
        assertEquals(15, result);  // 1 + 2 + 3 + 4 + 5 = 15
    }

    // Test with 0 (edge case)
    @Test
    public void testSumOfDigitsZero() {
    	long  result = SumOfDigits.sumOfDigits(0);
        assertEquals(0, result);  // Sum of digits of 0 is 0
    }

    // Test with a single digit number
    @Test
    public void testSumOfDigitsSingleDigit() {
    	long  result = SumOfDigits.sumOfDigits(7);
        assertEquals(7, result);  // Sum of digits of 7 is 7
    }

    // Test with a large number
    @Test
    public void testSumOfDigitsLargeNumber() {
    	long  result = SumOfDigits.sumOfDigits(98765430);
        assertEquals(42, result);  // 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + 0 = 45
    }

    
}
