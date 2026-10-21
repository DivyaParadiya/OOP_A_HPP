
import java.util.Scanner;

public class ChatDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob How are you",
            "malformed line"
        };

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        StringBuilder report = new StringBuilder();
        int count = 0;

        for (String line : logs) {
            String[] parts = line.split(" ", 3);

            if (parts.length < 3)
                continue;

            if (parts[2].toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                report.append(parts[0]).append(" ")
                      .append(parts[1]).append(": ")
                      .append(parts[2]).append("\n");
            }
        }

        System.out.println("Matches: " + count);
        System.out.print(report);

        sc.close();
    }
}
