package lab_7_SC;
import java.util.ArrayList;
import java.util.List;

public class StringPermutations {

    public static void main(String[] args) {
        // Check for command line arguments
        if (args.length == 0) {
            System.out.println("Usage: java StringPermutations <string>");
            return;
        }

        String input = args[0]; // Take the first argument as input
        List<String> permutations = generatePermutations(input);
        
        for (String perm : permutations) {
            System.out.println(perm);
        }
    }

    public static List<String> generatePermutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result; 
        }
        generatePermutationsHelper("", str, result);
        return result;
    }

    private static void generatePermutationsHelper(String prefix, String remaining, List<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
             
                generatePermutationsHelper(prefix + remaining.charAt(i),
                                            remaining.substring(0, i) + remaining.substring(i + 1), 
                                            result);
            }
        }
    }
}
