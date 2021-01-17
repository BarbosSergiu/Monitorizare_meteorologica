package ro.mta.se.lab.utils;

import org.jetbrains.annotations.NotNull;

public class StringUtils {
    @NotNull
    public static String capitalize(@NotNull String data) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] tokens = data.split("\\s");
        for (String token : tokens) {
            stringBuilder.append(token.substring(0, 1).toUpperCase())
                    .append(token.substring(1))
                    .append(" ");
        }

        return stringBuilder.toString();
    }
}
