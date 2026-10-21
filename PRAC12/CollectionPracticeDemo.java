
import java.util.*;

public class CollectionPracticeDemo {
    public static void main(String[] args) {
        System.out.println("1. WORD FREQUENCY");

        String paragraph = "java is easy and java is powerful and java is popular";

        String[] words = paragraph.toLowerCase().split("\\W+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        List<String> sortedWords = new ArrayList<>(frequency.keySet());

        sortedWords.sort((a, b) -> frequency.get(b) - frequency.get(a));

        for (String word : sortedWords) {
            System.out.println(word + " : " + frequency.get(word));
        }

        System.out.println("\n2. UNIQUE VISITORS");

        List<String> logins = Arrays.asList(
                "Aarav", "Diya", "Riya", "Aarav",
                "Kabir", "Diya", "Meera", "Riya"
        );

        HashSet<String> uniqueUsers = new HashSet<>(logins);
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(logins);

        System.out.println("Unique visitors: " + uniqueUsers.size());

        System.out.println("First three distinct names:");

        int count = 0;

        for (String name : orderedUsers) {
            System.out.println(name);
            count++;

            if (count == 3)
                break;
        }
    }
}
