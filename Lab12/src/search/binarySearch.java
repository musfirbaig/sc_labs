package search;
import java.util.ArrayList;
import java.util.List;

public class binarySearch {

    /**
     * Performs a recursive binary search on a sorted array.
     * This method is generic to handle both Integer and String arrays.
     * @param arr The sorted array of integers or strings.
     * @param target The target value to search for.
     * @param low The starting index of the search range.
     * @param high The ending index of the search range.
     * @return A list of indices where the target value is found.
     */
    public static <T extends Comparable<T>> List<Integer> binarySearchRecursive(T[] arr, T target, int low, int high) {
        List<Integer> result = new ArrayList<>();

        // Base case: If the search range is invalid, return an empty list
        if (arr == null || arr.length == 0 || low > high) {
            return result;
        }

        // Calculate the middle index
        int mid = low + (high - low) / 2;

        if (arr[mid].compareTo(target) == 0) {
            // Add the found index to the result list
            result.add(mid);
            
            // Search for other occurrences to the left of the mid
            int left = mid - 1;
            while (left >= low && arr[left].compareTo(target) == 0) {
                result.add(left);
                left--;
            }
            
            // Search for other occurrences to the right of the mid
            int right = mid + 1;
            while (right <= high && arr[right].compareTo(target) == 0) {
                result.add(right);
                right++;
            }
        } else if (arr[mid].compareTo(target) > 0) {
            // If target is smaller, search in the left half
            result.addAll(binarySearchRecursive(arr, target, low, mid - 1));
        } else {
            // If target is larger, search in the right half
            result.addAll(binarySearchRecursive(arr, target, mid + 1, high));
        }
        
        return result;
    }

    /**
     * A utility method that wraps the recursive binary search.
     * It initializes the search range with the full array range.
     * @param arr The sorted array of integers or strings.
     * @param target The target value to search for.
     * @return A list of indices where the target value is found.
     */
    public static <T extends Comparable<T>> List<Integer> search(T[] arr, T target) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }
}
