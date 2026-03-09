import java.util.Scanner;

public class MenuUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String msg) {
        System.out.print(msg);
        return scanner.nextInt();
    }

    public static double readDouble(String msg) {
        System.out.print(msg);
        return scanner.nextDouble();
    }

    public static String readString(String msg) {
        scanner.nextLine();
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void printHeader(String title) {
        System.out.println("\n========== " + title + " ==========");
    }

    public static void printDivider() {
        System.out.println("-----------------------------------");
    }
}