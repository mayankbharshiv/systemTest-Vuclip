package com.vuclip.premiumengg.automation.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHelper {
    /**
     * @param type
     * @param jsonObject
     * @param jsonElement
     * @return
     */
    public static <T> String remove(Class<T> type, T jsonObject, String jsonElement) {

        String jObj = new GsonBuilder().create().toJson(jsonObject, type);
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(jObj);
        jo.remove(jsonElement);
        return jo.toString();
    }

}
