package util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;
@Slf4j
public class GsonUtil {
    private GsonUtil() {
    }

    public static <T> List<T> jsonToList(String json, Type type){
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);

        }catch (JsonSyntaxException e){
           e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String json,Class<T> clazz)  {
        Gson gson = new Gson();
        return gson.fromJson(json,clazz);
    }

    public static String objectToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }



}
