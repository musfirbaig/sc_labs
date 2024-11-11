package lab_7_SC;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringPermutationsTest {

    @Test
    public void testPermutationsOfABC() {
        List<String> result = StringPermutations.generatePermutations("abc");
        assertEquals(6, result.size());
        assertTrue(result.contains("abc"));
        assertTrue(result.contains("acb"));
        assertTrue(result.contains("bac"));
        assertTrue(result.contains("bca"));
        assertTrue(result.contains("cab"));
        assertTrue(result.contains("cba"));
    }

    @Test
    public void testEmptyString() {
        List<String> result = StringPermutations.generatePermutations("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullString() {
        List<String> result = StringPermutations.generatePermutations(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleCharacter() {
        List<String> result = StringPermutations.generatePermutations("a");
        assertEquals(1, result.size());
        assertTrue(result.contains("a"));
    }
    @Test
    public void testDoubleCharacter() {
    	List<String> result = StringPermutations.generatePermutations("ab");
    	assertEquals(2, result.size());
    	assertTrue(result.contains("ab"));
    	assertTrue(result.contains("ba"));
    }
}
