class InputValidator {
    public static boolean isValid(String input) {
        return input.matches("[0-9]+");
    }

    public static boolean isValid(String input, int length) {
        return input.matches("[0-9]{" + length + "}");
    }
}
