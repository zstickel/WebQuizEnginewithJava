import java.math.BigDecimal;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        BigDecimal result = scanner.nextBigDecimal().add(scanner.nextBigDecimal()).add(scanner.nextBigDecimal());
        System.out.println(result);
    }
}