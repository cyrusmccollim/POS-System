import java.io.*;
import java.util.*;

public class DatabaseReader {
    public static ArrayList<String[]> readFile(String path) {
        ArrayList<String[]> dataLines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> fileLines = reader.lines().toList();
            for (String fileLine : fileLines)
                dataLines.add(fileLine.strip().split(","));
        } catch (Exception e){
            System.out.println("Unable to read file.");
        }
        return dataLines;
    }
}
