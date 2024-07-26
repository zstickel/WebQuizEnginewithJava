import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        BigDecimal startingAmount = scanner.nextBigDecimal();
        BigDecimal interestRate = scanner.nextBigDecimal();
        int yearsAccrued = scanner.nextInt();
        BigDecimal interestMultiplier = BigDecimal.ONE.add(interestRate.divide(new BigDecimal("100")));
        BigDecimal finalAmount = startingAmount.multiply(interestMultiplier.pow(yearsAccrued)).setScale(2,RoundingMode.CEILING);
        System.out.println("Amount of money in the account: " + finalAmount);
    }
}