package functions;
import java.util.*;

public class StringPalindromeUsingFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word: ");
        String word = scanner.nextLine();

        if (isPalindrome(word)) {
            System.out.println(word + " is a palindrome.");
        } else {
            System.out.println(word + " is not a palindrome.");
        }
        scanner.close();
    }

    // Method to check if a word is palindrome
    public static boolean isPalindrome(String str) {
        str = str.toLowerCase(); // ignore case
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // mismatch
            }
            left++;
            right--;
        }
        return true; // all matched
    }
}
