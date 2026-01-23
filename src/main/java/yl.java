import java.util.Scanner;
import java.util.ArrayList;

public class yl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm yl");
        System.out.println("What can I do for you");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon !");
                break;

            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
            } else if (userInput.startsWith("mark ")) {
                int num = Integer.parseInt((userInput.split(" ")[1])) - 1;
                tasks.get(num).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(num).toString());
            } else if (userInput.startsWith("unmark ")) {
                int num = Integer.parseInt((userInput.split(" ")[1])) - 1;
                tasks.get(num).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(num).toString());
            } else {
                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println("added: " + userInput);
            }
        }

    }
}
