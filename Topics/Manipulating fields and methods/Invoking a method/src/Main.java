import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodsDemo {

    public static void main(String[] args) {
        // write your code here
        try {
            // Get the Class object for SomeClass
            Class<?> someClass = SomeClass.class;

            // Get all methods of SomeClass
            Method[] methods = someClass.getDeclaredMethods();

            // Ensure there is only one method
            if (methods.length == 1) {
                Method method = methods[0];

                // Make the method accessible if it's not public
                method.setAccessible(true);

                // Create an instance of SomeClass
                Object instance = someClass.getDeclaredConstructor().newInstance();

                // Invoke the method with no arguments and print the result
                Object result = method.invoke(instance);
                System.out.println(result);
            } else {
                System.out.println("There should be only one method in SomeClass.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

