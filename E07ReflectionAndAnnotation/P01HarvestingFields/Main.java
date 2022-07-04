package E07ReflectionAndAnnotation.P01HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Field>> fieldsMap = getMapOfFields();

        String accessor = scanner.nextLine();
        while (!accessor.equals("HARVEST")){
            switch (accessor){
                case "private":
                    fieldsMap.get("private").forEach(Main::printField);
                    break;
                case "public":
                    fieldsMap.get("public").forEach(Main::printField);
                    break;
                case "protected":
                    fieldsMap.get("protected").forEach(Main::printField);
                    break;
                case "all":
                    fieldsMap.get("all").forEach(Main::printField);
                    break;
            }

            accessor = scanner.nextLine();
        }
    }

    private static Map<String, List<Field>> getMapOfFields() {
        Map<String, List<Field>> map = new HashMap<>();
        map.put("private", new ArrayList<>());
        map.put("public", new ArrayList<>());
        map.put("protected", new ArrayList<>());

        List<Field> allFields = Arrays.asList(RichSoilLand.class.getDeclaredFields());
        map.put("all", allFields);

        allFields.forEach(field -> {
            String modifier = Modifier.toString(field.getModifiers());

            switch (modifier){
                case "private":
                    map.get("private").add(field);
                    break;
                case "public":
                    map.get("public").add(field);
                    break;
                case "protected":
                    map.get("protected").add(field);
                    break;
            }
        });
        return map;
    }

    private static void printField(Field field){
        System.out.println(Modifier.toString(field.getModifiers())
                + " " + field.getType().getSimpleName()
                + " " + field.getName());
    }
}
