package stepik.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputOutput {

    public static void main(String[] args) {
        File directory = new File("sample");
        File file = new File("sample/file1.txt");
        try {
            directory.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(file.getName());
        System.out.println(file.exists());
        System.out.println(directory.isDirectory());
        System.out.println(file.isFile());

        String path = "folder1/folder2/folder3";
        File directories = new File(path);
        List<File> files = new ArrayList<>();
        files.add(new File(path + "/A1.txt"));
        files.add(new File(path + "/A2.txt"));
        files.add(new File(path + "/A3.txt"));
        files.add(new File(path + "/B1.txt"));
        files.add(new File(path + "/B2.txt"));

        directories.mkdirs();

        for (File f : files) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File[] filtered = directories.listFiles((dir, name) -> name.startsWith("A"));
        for (File file1 : filtered) {
            System.out.println(file1.getAbsolutePath());
        }
    }
}
