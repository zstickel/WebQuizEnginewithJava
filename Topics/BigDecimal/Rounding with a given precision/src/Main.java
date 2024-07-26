import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        BigDecimal number = scanner.nextBigDecimal();
        int newScale = scanner.nextInt();
        System.out.println(number.setScale(newScale, RoundingMode.HALF_DOWN));
    }   
}