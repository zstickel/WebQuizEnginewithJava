import java.util.Scanner;
import java.lang.ref.SoftReference;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer num = scanner.nextInt();
        // inititalize an instance of SoftReference by passing num to the constructor
        SoftReference<Integer> softReference = new SoftReference<>(num);

        num = null;

        System.out.println(softReference.get());
    }
}