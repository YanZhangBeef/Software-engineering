package com.fit5136.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    /**
     * Read Database data
     * @param path database filepath
     * @return a List
     */
    public static List<String> readData(String path) {
        // create list to store file data
        List<String> data = new ArrayList<>();

        try {
            // create character buffered input stream
            BufferedReader br = new BufferedReader(new FileReader(path));
            // read data
            String line;
            while ((line = br.readLine()) != null) {
                // add data to list
                data.add(line);
            }
            // release resources
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Write data into database files
     * @param path database filepath
     * @param data data need to be stored
     */
    public static void writeData(String path, List<String> data) {
        try {
            // create character buffered output stream
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            // write data
            for (String s : data) {
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
            // release resources
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
