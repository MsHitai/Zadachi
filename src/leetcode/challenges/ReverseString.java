package leetcode.challenges;

/**
 * <a href="https://leetcode.com/problems/reverse-string/description/?envType=daily-question&envId=2024-06-02">...</a>
 */
public class ReverseString {

    public static void main(String[] args) {
        char[] a = {'h', 'e', 'l', 'l', 'o'};
        char[] b = {'H', 'a', 'n', 'n', 'a', 'h'};
        char[] c = {'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ',
                'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c', 'a', 'n', 'a', 'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a'};
        char[] d = {'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c', 'a', 'n', 'a',
                'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a'};
        reverseString(a);
        reverseString(b);
        reverseString(c);
        reverseString(d);
        System.out.println(a);// expected olleh
        System.out.println(b);// expected hannaH
        System.out.println(c);// expected amanaP :lanac a ,nalp a ,nam A
        System.out.println(d);// expected amanaP :lanac a ,nalp a ,nam A
    }

    public static void reverseString(char[] s) {
        int j = 0;
        char temp;
        int middle = s.length / 2;
        for (int i = s.length - 1; i >= middle; i--) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            j++;
        }
    }
}
