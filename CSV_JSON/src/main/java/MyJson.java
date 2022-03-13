import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public interface MyJson {
    public static String listToJson(List<Employee> list){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();

        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String fileNameJSON){
        try (FileWriter file = new FileWriter(fileNameJSON)) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readString(String fileNameJSON){
        String strJson = "Error";
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileNameJSON));
            JSONArray jsonObjects = (JSONArray) obj;
            strJson =  jsonObjects.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return strJson;
    }
}
