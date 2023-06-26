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
            long before = System.currentTimeMillis();
            byte[] bytes = new byte[1000];
            int countBytes = inputStream.read(bytes);
            StringBuilder sb = new StringBuilder();
            while (countBytes > 0) {
                sb.append(new String(bytes, 0, countBytes));
                countBytes = inputStream.read(bytes);
            }
            System.out.println(sb);

            long after = System.currentTimeMillis();
            System.out.println(after - before);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
