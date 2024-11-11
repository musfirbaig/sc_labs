package lab_7_SC;
import java.io.File;

public class RecursiveFileSearch {
    public static void main(String[] args) {
        // Check for correct number of arguments
        if (args.length != 2) {
            System.out.println("Usage: java RecursiveFileSearch <directory> <file_name>");
            return;
        }

        String directoryPath = args[0];
        String fileName = args[1];

        // Start the file search
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Error: The specified directory does not exist or is not a directory.");
            return;
        }

        // Call the recursive search function
        boolean found = searchFile(directory, fileName);
        if (!found) {
            System.out.println("File '" + fileName + "' not found in directory: " + directoryPath);
        }
    }

    /**
     * Recursively searches for the specified file within the given directory.
     * @param directory The directory to search.
     * @param fileName The name of the file to search for.
     * @return true if the file is found, false otherwise.
     */
    public static boolean searchFile(File directory, String fileName) {
        // Check if the provided File is a directory
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Provided path is not a directory: " + directory.getAbsolutePath());
        }

        // Get all files and subdirectories in the current directory
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                // If the file is a directory, search recursively
                if (file.isDirectory()) {
                    if (searchFile(file, fileName)) {
                        return true;
                    }
                } else if (file.getName().equals(fileName)) {
                    System.out.println("File found: " + file.getAbsolutePath());
                    return true;
                }
            }
        }
        return false; // File not found in this directory
    }

}
