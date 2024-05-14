package util;

public class PasswordUtil {

    public enum SecurityLevel {
        WEAK, MEDIUM, STRONG
    }
    public static SecurityLevel assessPassword(String password) {
        if (password.length() < 8) return SecurityLevel.WEAK;
        if (password.matches("(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).+")) return SecurityLevel.STRONG;
        if (password.matches("(?=.*[a-zA-Z])(?=.*\\d).+")) return SecurityLevel.MEDIUM;
        return SecurityLevel.WEAK;
    }
}
