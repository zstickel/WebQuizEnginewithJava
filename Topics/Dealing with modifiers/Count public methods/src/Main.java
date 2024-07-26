import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ReflectionUtils {

    public static int countPublicMethods(Class targetClass) {
        int numPublic = 0;
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method: methods) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                numPublic++;
            }
        }


        return numPublic;
    }
}