package sixth.sprint;

public class FindRepeats {

    public static void main(String[] args) {
        FindRepeats check = new FindRepeats();
        int count = check.numberOfRepeats("раз два три, раз два три", "раз");
        System.out.println(count);
        count = check.numberOfRepeats("Hello, world!", "goodbye");
        System.out.println(count);
        count = check.numberOfRepeats("раз два три, раз два три", "раз");
        System.out.println(count);

        System.out.println(check.capitalize("я не волшебник, я только учусь!"));
    }
    int numberOfRepeats(String text, String substring) {
        int count = 0;
        StringBuilder builder = new StringBuilder(text);
        while(builder.indexOf(substring) != -1) {
            builder.delete(0, (builder.indexOf(substring)+ substring.length()));
            count++;
        }
        return count;
    }

    public String capitalize(String str) {
        String temp = str.substring(0, 1);
        str = str.substring(1);
        str = temp.toUpperCase() + str;
        return str;
    }

    public boolean isPalindromeWord(String str) {
        StringBuilder builder = new StringBuilder(str);
        return str.equals(builder.reverse().toString());
    }

    public boolean isPalindromeLine(String str) {
        StringBuilder builder = new StringBuilder();
        String[] words = str.split(" ");
        for (String word : words) {
            builder.append(word);
        }

        return builder.toString().equals(builder.reverse().toString());
    }
}
