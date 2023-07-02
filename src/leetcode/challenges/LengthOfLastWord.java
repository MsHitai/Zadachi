package leetcode.challenges;

public class LengthOfLastWord {

    public static void main(String[] args) {
        String s = "Hello World";
        String s1 = "   fly me   to   the moon  ";
        String s2 = "luffy is still joyboy";

        long before = System.currentTimeMillis();

        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWord(s1));
        System.out.println(lengthOfLastWord(s2));

        long after = System.currentTimeMillis();
        System.out.println(after - before);

        System.out.println();

        long before2 = System.currentTimeMillis();

        System.out.println(lengthOfLastWord2(s));
        System.out.println(lengthOfLastWord2(s1));
        System.out.println(lengthOfLastWord2(s2));

        long after2 = System.currentTimeMillis();
        System.out.println(after2 - before2);
    }

    public static int lengthOfLastWord(String s) {
        int result = 0;
        String[] words = s.split(" ");
        String lastWord = words[words.length-1];
        result = lastWord.length();
        return result;
    }

    public static int lengthOfLastWord2(String s) {
        s = s.trim();
        int op = 0;
        for(int i = s.length()-1; i>=0; i--) {

            if(s.charAt(i) == ' '){
                break;
            }
            op++;
        }
        return op;
    }
}
