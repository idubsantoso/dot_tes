package com.mini.project.tes.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ObjectMapperUtil {

    public static String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); //TODO
        }

        return json;
    }

    public static  <T> String toJsonArray(List<T> objects) throws IOException {
        //final List<SortObj> objects = new ArrayList<SortObj>(2);
        //objects.add(new SortObj("a1","a2"));
        //objects.add(new SortObj("b1","b2"));

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, objects);

        final byte[] data = out.toByteArray();
        return new String(data);
    }

    public static <T> T  toObject(String s, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        T object = objectMapper.readValue(s, valueType);

        return object;
    }

    public static <T> List<T> toListObject(String s, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //T[] pp1 = objectMapper.readValue(s, valueType);

        // 2. convert JSON array to List of objects
        //List<T> listObject = Arrays.asList(objectMapper.readValue(s, valueType));

        List<T> listObject = objectMapper.readValue(s, new TypeReference<List<T>>(){});

        return listObject;
    }

    public static HashMap<String, Object> toHashMap(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> hashMap = objectMapper.convertValue(o, HashMap.class);
        return hashMap;
    }

    public static HashMap<String, String> toHashMap(String o) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> hashMap = objectMapper.convertValue(o, HashMap.class);
        return hashMap;
    }

    public static <T> T toObject(LinkedHashMap linkedHashMap, Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(linkedHashMap, valueType);
    }
}
