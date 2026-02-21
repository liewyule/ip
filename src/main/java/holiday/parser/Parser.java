package holiday.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import holiday.BotException;
import holiday.command.AddCommand;
import holiday.command.Command;
import holiday.command.DeleteCommand;
import holiday.command.ExitCommand;
import holiday.command.FindCommand;
import holiday.command.GreetingCommand;
import holiday.command.ListCommand;
import holiday.command.MarkCommand;
import holiday.command.SortCommand;



/**
 * Parses raw user string input into Command.
 * <p>
 *     Contains helper method to extract information and validates
 *     the input format.
 * </p>
 */
public class Parser {

    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";
    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";
    private static final String CMD_HELLO = "hello";
    private static final String CMD_SORT = "sort";
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
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
        case CMD_HELLO:
            return new GreetingCommand();
        case CMD_LIST:
            return new ListCommand();
        case CMD_MARK:
            return new MarkCommand(true, getIndex(userInput));
        case CMD_UNMARK:
            return new MarkCommand(false, getIndex(userInput));
        case CMD_TODO:
            String todoDescription = parseTodoArgs(userInput);
            return new AddCommand("todo", todoDescription, null, null);
        case CMD_DEADLINE:
            String deadlineDescription = parseDeadlineArgs(userInput)[0];
            String deadline = parseDeadlineArgs(userInput)[1];
            return new AddCommand("deadline", deadlineDescription, null, deadline);
        case CMD_EVENT:
            String eventDescription = parseEventArgs(userInput)[0];
            String from = parseEventArgs(userInput)[1];
            String to = parseEventArgs(userInput)[2];
            return new AddCommand("event", eventDescription, from, to);
        case CMD_DELETE:
            return new DeleteCommand(getIndex(userInput));
        case CMD_FIND:
            return new FindCommand(getFindKeywords(userInput));
        case CMD_BYE:
            return new ExitCommand();
        case CMD_SORT:
            String description = parseSortArgs(userInput);
            return new SortCommand(description);
        default:
            throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Extract the parameter use for sorting
     * @param userInput  Full command line entered by the user.
     * @return parameter use for sorting
     * @throws BotException If the user input format is not correct
     */
    public static String parseSortArgs(String userInput) throws BotException {
        String[] parts = userInput.split(" ");
        if (parts.length < 3) {
            throw new BotException("please specify the sort command in the format sort by name/sort by time");
        }
        return parts[2];
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
        assert userInput != null : "Input should not be null";

        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please specify a valid task number");
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
    public static String parseTodoArgs(String userInput) throws BotException {
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
    public static String[] getFindKeywords(String userInput) throws BotException {
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
    public static String[] parseDeadlineArgs(String userInput) throws BotException {

        //check task cannot be empty
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String descriptionAndDeadline = parts[1];

        //check the deadline cannot be empty
        String[] checkTime = descriptionAndDeadline.split(" /by ", 2);
        if (checkTime.length < 2 || checkTime[1].trim().isEmpty()) {
            throw new BotException("please specify a deadline");
        }
        String description = checkTime[0];
        String deadLine = checkTime[1];

        try {
            LocalDateTime.parse(deadLine, INPUT_FORMAT);
        } catch (Exception e) {
            throw new BotException("Deadline must be in the format YYYY-MM-DD HHMM (e.g., 2026-02-21 2359)");
        }

        return new String[]{description, deadLine};

    }

    /**
     * Extracts the description, from time and to time of a event task command
     *
     * @param userInput Event task command from user.
     * @return Event task description, from time and to time.
     * @throws BotException If the description or from time or to time is missing or empty.
     */
    public static String[] parseEventArgs(String userInput) throws BotException {
        //check task cannot be empty
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new BotException("task cannot be empty!!!");
        }
        String descriptionAndTime = parts[1];

        //check the start date cannot be empty
        String[] checkStart = descriptionAndTime.split(" /from ", 2);
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

        try {
            LocalDateTime.parse(start, INPUT_FORMAT);
            LocalDateTime.parse(end, INPUT_FORMAT);
        } catch (Exception e) {
            throw new BotException("specify the time in the format YYYY-MM-DD HHMM (e.g., 2026-02-21 2359)");
        }
        return new String[]{description, start, end};

    }
}
