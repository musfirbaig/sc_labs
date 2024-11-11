package lab_7_SC;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class RecursiveFileSearchTest {
    
    private Path tempDir;

    @BeforeEach
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("testDir");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.walk(tempDir)
             .sorted((a, b) -> b.compareTo(a)) // Delete files before directories
             .forEach(path -> path.toFile().delete());
    }

    @Test
    public void testFileFoundInRoot() throws IOException {
        Path testFile = tempDir.resolve("testFile.txt");
        Files.createFile(testFile);

       
        boolean found = RecursiveFileSearch.searchFile(tempDir.toFile(), "testFile.txt");
        
        
        assertTrue(found);
    }

    @Test
    public void testFileNotFound() throws IOException {
        
        boolean found = RecursiveFileSearch.searchFile(tempDir.toFile(), "nonExistentFile.txt");
        
       
        assertFalse(found);
    }

    @Test
    public void testFileFoundInSubdirectory() throws IOException {
        
        Path subDir = tempDir.resolve("subDir");
        Files.createDirectory(subDir);
        Path testFile = subDir.resolve("nestedFile.txt");
        Files.createFile(testFile);

        
        boolean found = RecursiveFileSearch.searchFile(tempDir.toFile(), "nestedFile.txt");
        
        
        assertTrue(found);
    }

    @Test
    public void testFileFoundInDeepSubdirectory() throws IOException {
        
        Path deepSubDir = tempDir.resolve("level1/level2/level3");
        Files.createDirectories(deepSubDir);
        Path testFile = deepSubDir.resolve("deepFile.txt");
        Files.createFile(testFile);

        
        boolean found = RecursiveFileSearch.searchFile(tempDir.toFile(), "deepFile.txt");
        
       
        assertTrue(found);
    }

    @Test
    public void testInvalidDirectory() {
       
        File nonDirectoryFile = new File(tempDir.toFile(), "testFile.txt");
        assertThrows(IllegalArgumentException.class, () -> {
            RecursiveFileSearch.searchFile(nonDirectoryFile, "testFile.txt");
        });
    }
}
