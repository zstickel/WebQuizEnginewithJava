import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        String third = scanner.nextLine();
        BigDecimal firstDecimal = new BigDecimal(first);
        BigDecimal secondDecimal = new BigDecimal(second);
        BigDecimal thirdDecimal = new BigDecimal(third);
        BigDecimal sum = firstDecimal.add(secondDecimal).add(thirdDecimal);
        BigDecimal average = sum.divide(new BigDecimal("3.0"),0,RoundingMode.DOWN);
        System.out.println(average);
    }
}