package app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class JsonUtil {
    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException while mapping object (" + data + ") to JSON");
        }
    }
    
    public static <T> T jsonToObject(String jsonText, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(jsonText, clazz);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public static Map<String, Object> jsonToMap(String jsonText) {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(Map.class, String.class, Object.class);
        try {
            return mapper.readValue(jsonText, mapType);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
