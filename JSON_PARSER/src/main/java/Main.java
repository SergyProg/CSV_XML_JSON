import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Employee> jsonToList(String strJson){
        List<Employee> list = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonObjects = (JSONArray) jsonParser.parse(strJson);
            for (Object jsonObject:jsonObjects) {
                Gson gson = new GsonBuilder().create();
                list.add(gson.fromJson(jsonObject.toString(), Employee.class));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        String fileNameJSON = "data2.json";
        // интерфейс MyJson и класс Employee описаны в модуле CSV_JSON данного проекта
        // метод readString() реализован в интерфейсе MyJson т.к. по логике работы относится
        // к данному интерфейсу, созданному ранее и общему для всех 3-х заданий

        List<Employee> list = jsonToList(MyJson.readString(fileNameJSON));
        for (Employee employee: list){
            System.out.println(employee);
        }

    }
}
