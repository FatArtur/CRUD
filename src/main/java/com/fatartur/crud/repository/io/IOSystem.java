package main.java.com.fatartur.crud.repository.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOSystem {
    private final static String pathToFile = "/Users/Artur/IdeaProjects/CRUD/src/main/resources/";

    public static List<String> read(String filename){
        List<String> list = new ArrayList<>();

        try (BufferedReader reader= new BufferedReader(new InputStreamReader
                (new FileInputStream(pathToFile+filename))))
        { String line;
        while ((line = reader.readLine()) != null){
            list.add(line);
        }
        } catch (IOException e){
            System.err.println(e);
        }
        return list;
    }
    public static void write(String filename, List<String> list){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile + filename)))
        {
            for (String txt: list) {
                bw.write(txt);
                bw.newLine();
            }
        } catch (IOException e){
            System.err.println(e);
        }
    }


    
}
