package br.com.adotappet.adotappetapi.util;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonLoader {

    public <T> T loadBeanFromJson(Class<T> type, String location) {
        final InputStream inputJsonData = this.getClass().getClassLoader().getResourceAsStream(location);
        return loadBeanFromInputStream(type, inputJsonData);
    }

    private <T> T loadBeanFromInputStream(Class<T> type, InputStream inputJsonData) {
        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
                (JsonDeserializer<LocalDate>) (jsonElement, type1, jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        .create();
        return gson.fromJson(new InputStreamReader(inputJsonData), type);
    }
}
