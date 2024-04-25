package com.metlife.hotel.util;


import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class IdGenerator {

    private static String FILE_PATH = "C:" + File.separator + "New folder" + File.separator + "latest_id.txt";

    private final Object lock = new Object();

//    @Autowired
//    private final Environment env;

//    @Autowired
//    public IdGenerator(Environment env) {
//        this.env = env;
//        FILE_PATH = env.getProperty("file_path");
//    }

    public String generateId() {
        synchronized (lock) {
            int lastId = readLastIdFromFile();
            lastId++;
            writeLastIdToFile(lastId);
            return String.format("%c%04d", 'H', lastId);
        }
    }

    private int readLastIdFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0; // Default to 0 if file doesn't exist or cannot be read
    }

    private void writeLastIdToFile(int lastId) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(String.valueOf(lastId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
