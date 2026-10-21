package service;

public class Validator {
    public static boolean validAccountNumber(String number) {
        return number != null && !number.isBlank();
    }

    public static boolean validName(String name) {
        return name != null && !name.isBlank();
    }

    public static boolean validAmount(long amount) {
        return amount > 0;
    }
}
