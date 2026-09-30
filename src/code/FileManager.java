package Final;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static FileManager instance;

    private FileManager() {
        
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

   
    public void saveToTxt(String fileName, String content) {

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing TXT file: " + e.getMessage());
        }
    }
    
    
    public void saveToXml(String fileName, String content) {

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.write("<report>\n");
            writer.write("<content>\n");
            writer.write(content + "\n");
            writer.write("</content>\n");
            writer.write("</report>");

        } catch (IOException e) {
            System.out.println("Error writing XML file: " + e.getMessage());
        }
    }
}
