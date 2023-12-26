class FileManager {

    public static void save(Object object) {
        System.out.println("Saving " + object + "...");
    }

    public static void save(Object object, String fileName) {
        System.out.println("Saving " + object + " to " + fileName + "...");
    }
}
