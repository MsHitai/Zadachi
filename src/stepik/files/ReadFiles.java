package stepik.files;

import java.io.*;

public class ReadFiles {

    public static void main(String[] args) {
        File file = new File("1.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream(file)) {
            int a = inputStream.read();
            while (a != -1) {
                System.out.print((char) a);
                a = inputStream.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
