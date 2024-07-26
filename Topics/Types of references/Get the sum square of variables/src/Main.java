import java.util.Scanner;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer num1 = scanner.nextInt();   
        Integer num2 = scanner.nextInt();
        // Initialize an instance of SoftReference by passing num1 to the constructor
        SoftReference<Integer> softReference = new SoftReference<>(num1);
        // Initialize an instance of WeakReference by passing num2 to the constructor
        WeakReference<Integer> weakReference = new WeakReference<>(num2);

        num1 = null;
        num2 = null;

        System.out.println((int) Math.pow(softReference.get() + weakReference.get(), 2));
    }
}