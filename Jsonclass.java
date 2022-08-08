import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jsonclass {
     public Map <String,List> lines = new HashMap<>();
     public Map <String,List> trips = new HashMap<>();

    public static String readJson(String jsonPath) {
        File jsonFile = new File(jsonPath);
        try {
            FileReader fileReader = new FileReader(jsonFile);
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                int ch = reader.read();
                if (ch != -1) {
                    sb.append((char) ch);
                } else {
                    break;
                }
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
