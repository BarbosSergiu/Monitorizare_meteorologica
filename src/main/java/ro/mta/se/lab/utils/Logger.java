package ro.mta.se.lab.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FILE = "log.txt";

    public static void Log(String data) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            fileWriter.write(formatter.format(date) + " : ");
            fileWriter.write(data + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
