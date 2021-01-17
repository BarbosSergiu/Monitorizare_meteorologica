package ro.mta.se.lab.utils;

import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class JsonParser {

    @NotNull
    @Contract("_ -> new")
    public static Response parse(String jsonString) {

        Gson gson = new Gson();

        return gson.fromJson(jsonString, Response.class);
    }

}
