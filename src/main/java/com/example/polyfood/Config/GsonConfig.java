package com.example.polyfood.Config;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class GsonConfig {
    public static Gson getGsonWithLocalDateTimeAdapter() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
    }
}
