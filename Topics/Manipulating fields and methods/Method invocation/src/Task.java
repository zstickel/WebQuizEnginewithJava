// You can experiment here, it won’t be checked

import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ClassLoader classLoader = scanner.getClass().getClassLoader();
    System.out.println(classLoader);
  }
}

