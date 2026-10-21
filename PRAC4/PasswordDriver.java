
public class PasswordDriver {
    public static void main(String[] args) {
        String[] passwords = {"abc", "abcdefgh", "Abcdefgh", "Abcd1234!"};

        for (String pw : passwords) {
            System.out.println("Password: " + pw);

            System.out.println("Length >= 8: " + (pw.length() >= 8));
            System.out.println("Uppercase: " + pw.matches(".*[A-Z].*"));
            System.out.println("Digit: " + pw.matches(".*[0-9].*"));
            System.out.println("Special character: " + pw.matches(".*[^a-zA-Z0-9].*"));

            System.out.println("Strength: " + PasswordChecker.strength(pw));
            System.out.println();
        }
    }
}
