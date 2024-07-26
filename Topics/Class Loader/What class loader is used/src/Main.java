class Main {
    public static void main(String... args) {
        // code
        ClassLoader classLoader = Main.class.getClassLoader();
        System.out.println(classLoader.getName());
    }
}