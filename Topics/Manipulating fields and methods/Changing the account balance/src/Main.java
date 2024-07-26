import java.lang.reflect.Field;

final class AccountUtils {

    private AccountUtils() { }

    public static void increaseBalance(Account account, long amount) {
        // write your code here
        try {
            Class<?> accountClass = account.getClass();
            Field field = accountClass.getDeclaredField("balance");
            field.setAccessible(true);
            long currentBalance = (long) field.get(account);
            long newBalance = currentBalance + amount;
            field.set(account, newBalance);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}

