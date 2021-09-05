package main.com.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

public class Deserializer implements JsonDeserializer<Map<String,Object>> {
    @Override
    public Map<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return jsonElement.getAsJsonObject()
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(Map.Entry::getKey,e->e.getValue().toString())
                );

    }
}
