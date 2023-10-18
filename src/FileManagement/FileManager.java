package FileManagement;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static File directory = new File("C:\\gestao");

    public static void createDirectory(){
        if(!directory.exists()){
            directory.mkdir();
        }
    }

    public static File createFile(String fileName) {
        createDirectory();
        File file = new File(directory.getAbsoluteFile().toString().concat("\\" + fileName));

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return file;
    }
}
