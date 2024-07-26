import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class Main {
    static {
        System.out.println("Creating class");
    }
    public static void main(String[] args)throws ClassNotFoundException {
        System.out.println(Class.forName(String.class.getClass().getName()));
    }
}