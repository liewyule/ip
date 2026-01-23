import java.util.Scanner;

public class yl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm yl");
        System.out.println("What can I do for you");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon !");
                break;
            }
            System.out.println(userInput);
        }

    }
}
