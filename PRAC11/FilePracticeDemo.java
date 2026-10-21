
import java.io.*;
import java.nio.file.*;
import java.util.*;

class StudentRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    int marks;
    transient String password;

    StudentRecord(String name, int marks, String password) {
        this.name = name;
        this.marks = marks;
        this.password = password;
    }

    public String toString() {
        return "Name: " + name + ", Marks: " + marks
                + ", Password: " + password;
    }
}

public class FilePracticeDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("1. SAVE AND LOAD OBJECTS");

        StudentRecord[] students = {
            new StudentRecord("Aarav", 85, "abc123"),
            new StudentRecord("Diya", 92, "xyz456"),
            new StudentRecord("Riya", 78, "pass789")
        };

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }

        StudentRecord[] loadedStudents;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("students.dat"))) {
            loadedStudents = (StudentRecord[]) in.readObject();
        }

        for (StudentRecord s : loadedStudents)
            System.out.println(s);

        System.out.println("\n2. LOG ANALYZER");

        Path folder = Paths.get("logs");
        Files.createDirectories(folder);

        Files.write(folder.resolve("log1.txt"), Arrays.asList(
                "Application started",
                "User logged in",
                "ERROR: file not found"
        ));

        Files.write(folder.resolve("log2.txt"), Arrays.asList(
                "Application running",
                "ERROR: connection failed",
                "User logged out"
        ));

        Files.write(folder.resolve("log3.txt"), Arrays.asList(
                "ERROR: invalid input",
                "Application closed"
        ));

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(folder, "*.txt")) {
            for (Path file : files) {
                int fileLines = 0;
                int fileMatches = 0;

                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        fileLines++;
                        totalLines++;

                        if (line.contains(keyword)) {
                            fileMatches++;
                            matchingLines++;
                        }
                    }
                }

                System.out.println(file.getFileName()
                        + " | Size: " + Files.size(file) + " bytes"
                        + " | Lines: " + fileLines
                        + " | Matching lines: " + fileMatches);
            }
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": " + matchingLines);
    }
}
