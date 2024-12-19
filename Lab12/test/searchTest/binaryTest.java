package searchTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import search.binarySearch;
import java.util.List;

public class binaryTest {

    @Test
    public void testSearchElementFound() {
        Integer[] sortedArray = {2, 4, 6, 8, 10, 12, 14};
        int target = 10;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Only one occurrence of 10
        assertTrue(result.contains(4));  // Target 10 should be found at index 4
    }

    @Test
    public void testSearchElementNotFound() {
        Integer[] sortedArray = {1, 3, 5, 7, 9, 11};
        int target = 6;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // Target 6 is not present in the array
    }

    @Test
    public void testSearchEmptyArray() {
        Integer[] sortedArray = {};
        int target = 5;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // Empty array should always return an empty list
    }

    @Test
    public void testSearchNullArray() {
        Integer[] sortedArray = null;
        int target = 5;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // Null array should always return an empty list
    }

    @Test
    public void testSearchSingleElementFound() {
        Integer[] sortedArray = {7};
        int target = 7;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Only one occurrence of 7
        assertTrue(result.contains(0));  // Single element array should return index 0
    }

    @Test
    public void testSearchSingleElementNotFound() {
        Integer[] sortedArray = {7};
        int target = 5;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // Single element array should return an empty list when target does not match
    }

    @Test
    public void testSearchFirstElement() {
        Integer[] sortedArray = {10, 20, 30, 40, 50};
        int target = 10;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Target 10 should be found only at index 0
        assertTrue(result.contains(0));  // First element should be found at index 0
    }

    @Test
    public void testSearchLastElement() {
        Integer[] sortedArray = {10, 20, 30, 40, 50};
        int target = 50;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Target 50 should be found only at index 4
        assertTrue(result.contains(4));  // Last element should be found at index 4
    }

    @Test
    public void testSearchMiddleElement() {
        Integer[] sortedArray = {10, 20, 30, 40, 50};
        int target = 30;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Target 30 should be found only at index 2
        assertTrue(result.contains(2));  // Middle element should be found at index 2
    }

    @Test
    public void testSearchNegativeNumbers() {
        Integer[] sortedArray = {-10, -5, 0, 5, 10};
        int target = -5;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // Target -5 should be found only at index 1
        assertTrue(result.contains(1));  // Target -5 should be found at index 1
    }

    @Test
    public void testSearchMultipleOccurrences() {
        Integer[] sortedArray = {2, 4, 4, 4, 6, 8, 10};
        int target = 4;
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(3, result.size());  // Target 4 occurs 3 times
        assertTrue(result.contains(1));  // Target 4 should be found at indices 1, 2, and 3
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testSearchStringArray() {
        String[] sortedArray = {"apple", "banana", "banana", "cherry", "date"};
        String target = "banana";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(2, result.size());  // Target "banana" occurs twice
        assertTrue(result.contains(1));  // "banana" should be found at indices 1 and 2
        assertTrue(result.contains(2));
    }

    @Test
    public void testSearchSingleStringElementNotFound() {
        String[] sortedArray = {"apple"};
        String target = "banana";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // "banana" is not present in the array
    }

    @Test
    public void testSearchMultipleOccurrencesOfString() {
        String[] sortedArray = {"apple", "banana", "banana", "cherry", "date"};
        String target = "banana";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(2, result.size());  // "banana" occurs twice
        assertTrue(result.contains(1));  // "banana" should be found at indices 1 and 2
        assertTrue(result.contains(2));
    }

    @Test
    public void testSearchTargetNotPresent() {
        String[] sortedArray = {"apple", "banana", "cherry", "date"};
        String target = "fig";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // "fig" is not present in the array
    }

    @Test
    public void testSearchFirstElementFound() {
        String[] sortedArray = {"apple", "banana", "cherry", "date"};
        String target = "apple";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "apple" should be found only at index 0
        assertTrue(result.contains(0));  // First element should be found at index 0
    }

    @Test
    public void testSearchLastElementFound() {
        String[] sortedArray = {"apple", "banana", "cherry", "date"};
        String target = "date";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "date" should be found only at index 3
        assertTrue(result.contains(3));  // Last element should be found at index 3
    }

    @Test
    public void testSearchMiddleElementFound() {
        String[] sortedArray = {"apple", "banana", "cherry", "date"};
        String target = "cherry";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "cherry" should be found only at index 2
        assertTrue(result.contains(2));  // Middle element should be found at index 2
    }

    @Test
    public void testSearchStringsWithDifferentCases() {
        String[] sortedArray = {"apple", "banana", "Cherry", "date"};
        String target = "cherry";  // Check case sensitivity
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(0, result.size());  // "cherry" with lowercase 'c' should not be found, because the array contains "Cherry"
    }

    @Test
    public void testSearchStringsWithSpecialCharacters() {
        String[] sortedArray = {"apple", "banana", "cherry!", "date"};
        String target = "cherry!";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "cherry!" should be found at index 2
        assertTrue(result.contains(2));  // "cherry!" should be found at index 2
    }

    @Test
    public void testSearchStringsWithSpaces() {
        String[] sortedArray = {"apple", "banana", "cherry pie", "date"};
        String target = "cherry pie";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "cherry pie" should be found at index 2
        assertTrue(result.contains(2));  // "cherry pie" should be found at index 2
    }

    @Test
    public void testSearchStringArrayAllElementsSame() {
        String[] sortedArray = {"apple", "apple", "apple", "apple", "apple"};
        String target = "apple";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(5, result.size());  // "apple" should be found at all indices from 0 to 4
        assertTrue(result.contains(0));  // "apple" found at index 0
        assertTrue(result.contains(1));  // "apple" found at index 1
        assertTrue(result.contains(2));  // "apple" found at index 2
        assertTrue(result.contains(3));  // "apple" found at index 3
        assertTrue(result.contains(4));  // "apple" found at index 4
    }

    @Test
    public void testSearchLongString() {
        String[] sortedArray = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "kiwi"};
        String target = "elderberry";
        List<Integer> result = binarySearch.search(sortedArray, target);
        assertEquals(1, result.size());  // "elderberry" should be found at index 4
        assertTrue(result.contains(4));  // "elderberry" should be found at index 4
    }
}
