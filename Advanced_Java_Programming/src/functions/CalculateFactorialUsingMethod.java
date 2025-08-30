package functions;
import java.util.*;
public class CalculateFactorialUsingMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to calculate factorial: ");
        int num = scanner.nextInt();

        System.out.println("Factorial of 5 is: " + factorial(num));
    }

    public static int factorial(int num) {
        if (num == 1) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
