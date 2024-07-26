import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public String[] getPublicFields(Object object) {
        // Add implementation here
        List<String> publicFieldsList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers)){
                publicFieldsList.add(field.getName());
            }
        }
        return publicFieldsList.toArray(new String[0]);
    }

}