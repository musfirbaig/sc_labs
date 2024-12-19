package sum;

import java.util.Scanner;

public class SumOfDigits {

    // Recursive method to calculate the sum of digits of a number
    public static long sumOfDigits(long number) {
        // Handle negative numbers by converting them to positive
        number = Math.abs(number);
        
        // Base case: if the number is 0, return 0
        if (number == 0) {
            return 0;
        }
        
        // Recursive case: sum the last digit and call sumOfDigits with the remaining number
        return number % 10 + sumOfDigits(number / 10);
    }

    public static void main(String[] args) {
        // Scanner to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for a number
        System.out.print("Enter a non-negative integer: ");
        long number = scanner.nextInt();
        
        // Get the sum of digits
         long sum = sumOfDigits(number);
        
        // Output the result
        System.out.println("Sum of digits: " + sum);
        
        scanner.close();
    }
}
