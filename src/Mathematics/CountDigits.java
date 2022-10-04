package Mathematics;

import java.util.Scanner;

public class CountDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter number:");
        int number = sc.nextInt();
        System.out.println(countDigits(number));
    }

    private static int countDigits(int number) {
        return (int) Math.log10(number) + 1;
    }
}