package stepik.multithreading;

import java.io.*;

public class ReadWrite {

    public static void main(String[] args) {
        File file = new File("users.usr");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*User user = new User("John", "Smith", 25);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            User user = (User) objectInputStream.readObject();
            System.out.println(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class User implements Serializable {
        private final String name;
        private final String lastName;
        private final int age;

        public String getName() {
            return name;
        }

        public User(String name, String lastName, int age) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

/*File directory = new File("folder");
        File file = new File(directory, "names.txt");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter next page or \"stop\" to exit");
            byte[] pageText = new byte[3000];
            String input = scanner.nextLine();
            while (!input.equals("stop")) {
                int page = Integer.parseInt(input);
                randomAccessFile.seek((page - 1) * pageText.length);
                int count = randomAccessFile.read(pageText);
                System.out.println(new String(pageText, 0, count));
                System.out.println("Enter next page or \"stop\" to exit");
                input = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
