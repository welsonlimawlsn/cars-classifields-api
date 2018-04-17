package br.com.welson.carsclassifieds.security.util;

import java.util.HashMap;
import java.util.Map;

public class JsonGenerator {

    private Map<String, String> map;

    public JsonGenerator() {
        map = new HashMap<>();
    }

    public JsonGenerator put(String key, String value) {
        map.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(500);
        stringBuilder.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
