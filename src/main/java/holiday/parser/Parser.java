package holiday.parser;

import holiday.BotException;
import holiday.command.AddCommand;
import holiday.command.Command;
import holiday.command.DeleteCommand;
import holiday.command.ExitCommand;
import holiday.command.FindCommand;
import holiday.command.GreetingCommand;
import holiday.command.ListCommand;
import holiday.command.MarkCommand;

/**
 * Parses raw user string input into Command.
 * <p>
 *     Contains helper method to extract information and validates
 *     the input format.
 * </p>
 */
public class Parser {

    /**
     * Parses the user raw input and returns the corresponding command.
     *
     * @param userInput Full command entered by user.
     * @return Command instances that representing the user input.
     * @throws BotException If the command is not recognize.
     */
    public static Command parse(String userInput) throws BotException {
        String taskType = userInput.split(" ", 2)[0];
        switch (taskType) {
        case "hello":
            return new GreetingCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(true, getIndex(userInput));
        case "unmark":
            return new MarkCommand(false, getIndex(userInput));
        case "todo":
            String todoDescription = getTodo(userInput);
            return new AddCommand("todo", todoDescription, null, null);
        case "deadline":
            String deadlineDescription = getDeadline(userInput)[0];
            String deadline = getDeadline(userInput)[1];
            return new AddCommand("deadline", deadlineDescription, null, deadline);
        case "event":
            String eventDescription = getEvent(userInput)[0];
            String from = getEvent(userInput)[1];
            String to = getEvent(userInput)[2];
            return new AddCommand("event", eventDescription, from, to);
        case "delete":
            return new DeleteCommand(getIndex(userInput));
        case "find":
            return new FindCommand(getFind(userInput));
        case "bye":
            return new ExitCommand();
        default:
            throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Extracts whether the given string is a number
     *
     * @param s String to check
     * @return True if s is not an integer and false otherwise.
     */
    public static boolean isNotInteger(String s) {
        return !s.matches("\\d+");
    }

    /**
     * Extracts the task index from commands that require an index.
     * (e.g., mark, unmark, delete).
     *
     * @param userInput Full command line entered by the user.
     * @return Zero-based task index.
     * @throws BotException  If the index is missing or not a valid number.
     */
    public static int getIndex(String userInput) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task number");
        }
        return Integer.parseInt(checkNum) - 1;
    }

    /**
     * Extracts the description of a todos task command.
     *
     * @param userInput Todos task command from user.
     * @return Todos task description
     * @throws BotException If the description is missing or empty.
     */
    public static String getTodo(String userInput) throws BotException {
        //check task description cannot be empty
        String[] task = userInput.split(" ", 2);
        if (task.length < 2 || task[1].trim().isEmpty()) {
            throw new BotException("task description cannot be empty!!!");
        }
        return task[1];
    }

    /**
     * Extracts the find keyword of a find command.
     *
     * @param userInput Find command from user.
     * @return Keywords of the find command
     * @throws BotException If the keyword is missing or empty.
     */
    public static String[] getFind(String userInput) throws BotException {
        //check find description cannot be empty
        String[] task = userInput.split(" ", 2);
        if (task.length < 2 || task[1].trim().isEmpty()) {
            throw new BotException("Find keyword cannot be empty!!!");
        }
        String[] keywords = task[1].trim().split("\\s+");
        return keywords;
    }

    /**
     * Extracts the description and  deadline of a deadline task command.
     *
     * @param userInput Deadline task command from user.
     * @return Deadline task description and deadline.
     * @throws BotException If the description or deadline is missing or empty.
     */
    public static String[] getDeadline(String userInput) throws BotException {

        //check task cannot be empty
        String[] deadline = userInput.split(" ", 2);
        if (deadline.length < 2 || deadline[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String deadLineTask = deadline[1];

        //check the deadline cannot be empty
        String[] checkTime = deadLineTask.split(" /by ", 2);
        if (checkTime.length < 2 || checkTime[1].trim().isEmpty()) {
            throw new BotException("pls specify a deadline");
        }
        String description = checkTime[0];
        String deadLine = checkTime[1];
        return new String[]{description, deadLine};

    }

    /**
     * Extracts the description, from time and to time of a event task command
     *
     * @param userInput Event task command from user.
     * @return Event task description, from time and to time.
     * @throws BotException If the description or from time or to time is missing or empty.
     */
    public static String[] getEvent(String userInput) throws BotException {
        //check task cannot be empty
        String[] checkEvent = userInput.split(" ", 2);
        if (checkEvent.length < 2 || checkEvent[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String eventTask = checkEvent[1];

        //check the start date cannot be empty
        String[] checkStart = eventTask.split(" /from ", 2);
        if (checkStart.length < 2 || checkStart[1].trim().isEmpty()) {
            throw new BotException("pls specify when the event start!");
        }

        String description = checkStart[0];
        String checkTime = checkStart[1];

        //check the end date cannot be empty
        String[] checkEnd = checkTime.split(" /to ", 2);
        if (checkEnd.length < 2 || checkEnd[1].trim().isEmpty()) {
            throw new BotException("pls specify when the event end!");
        }

        String start = checkEnd[0];
        String end = checkEnd[1];
        return new String[]{description, start, end};

    }
}
