package union.xenfork.nucleoplasm.json.edit.gson;

import com.google.gson.annotations.SerializedName;
import oshi.util.tuples.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class ClassGson {
    @SerializedName("class")
    private String class_name;
    @SerializedName("extends")
    private ClassGson extend;
    @SerializedName("implements")
    private ArrayList<ClassGson> implement;
//    @SerializedName("strings")
//    private ArrayList<Pair<String, String>> stringField;
//    @SerializedName("ints")
//    private HashMap<String, Integer> integerField;
//    @SerializedName("floats")
//    private HashMap<String, Float> floatField;
//    @SerializedName("doubles")
//    private HashMap<String, Double> doubleField;
//    @SerializedName("longs")
//    private HashMap<String, Long> longField;
//    @SerializedName("objects")
//    private HashMap<String, ClassGson> objectField;

    @SerializedName("field")
    private ArrayList<Pair<String, Object>> field_pairs;

    public ClassGson(Class<?> clazz) {
        class_name = clazz.getName();
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            extend = new ClassGson(superclass);
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length >= 1) {
            implement = new ArrayList<>();
            for (Class<?> anInterface : interfaces) {
                implement.add(new ClassGson(anInterface));
            }
        }
    }

    public ClassGson(Object object) {
        this(object.getClass());
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length >= 1) {
            field_pairs = new ArrayList<>();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object o = field.get(object);
                        if (o instanceof String
                            || o instanceof Integer
                            || o instanceof Float || o instanceof Double || o instanceof Long || o instanceof Boolean) {
                            field_pairs.add(new Pair<>(field.getName(), o));
                        } else {
                            field_pairs.add(new Pair<>(field.getName(), new ClassGson(o)));
                        }
//                        if (o instanceof String) {
//                            if (stringField == null) stringField = new ArrayList<>();
//                            stringField.add(new Pair<>(field.getName(), o))
//                            stringField.put(field.getName(), (String) o);
//                        } else if (o instanceof Integer) {
//                            if (integerField == null) integerField = new HashMap<>();
//                            integerField.put(field.getName(), (Integer) o);
//                        } else if (o instanceof Float) {
//                            if (floatField == null) floatField = new HashMap<>();
//                            floatField.put(field.getName(), (Float) o);
//                        } else if (o instanceof Double) {
//                            if (doubleField == null) doubleField = new HashMap<>();
//                            doubleField.put(field.getName(), (Double) o);
//                        } else if (o instanceof Long) {
//                            if (longField == null) longField = new HashMap<>();
//                            longField.put(field.getName(), (Long) o);
//                        } else {
//                            if (objectField == null) objectField = new HashMap<>();
//                            objectField.put(field.getName(), new ClassGson(o));
////                            System.out.println(field.getName() + "-->" + o.getClass());
//                        }
                    } catch (IllegalAccessException ignored) {}
                }

            }
        }
    }
}
