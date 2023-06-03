package sixth.sprint;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("input.txt"))) {

            fileReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Writer out = new OutputStreamWriter(System.out, StandardCharsets.UTF_8)) {
            out.write(decodeText(
                    "Съешь ещё этих мягких французских булок да выпей же чаю"));

        } catch (IOException e) {
            // игнорируем
        }
    }

    static String decodeText(String input) throws IOException {
        return new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)).readLine();
    }
}
