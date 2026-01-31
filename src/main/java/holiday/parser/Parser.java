package holiday.parser;

import holiday.BotException;
import holiday.command.AddCommand;
import holiday.command.Command;
import holiday.command.DeleteCommand;
import holiday.command.ExitCommand;
import holiday.command.ListCommand;
import holiday.command.MarkCommand;

public class Parser {

    public static Command parse(String userInput) throws BotException {
        String taskType = userInput.split(" ", 2)[0];
        switch (taskType) {
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
        case "bye":
            return new ExitCommand();
        default:
            throw new BotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static boolean isNotInteger(String s) {
        return !s.matches("\\d+");
    }

    public static int getIndex(String userInput) throws BotException {
        String checkNum = userInput.split(" ")[1];

        //check the input after mark is a number
        if (isNotInteger(checkNum)) {
            throw new BotException("please indicate the task number");
        }
        return Integer.parseInt(checkNum) - 1;
    }

    public static String getTodo(String userInput) throws BotException {
        //check task description cannot be empty
        String[] task = userInput.split(" ", 2);
        if (task.length < 2 || task[1].trim().isEmpty()) {
            throw new BotException("task description cannot be empty!!!");
        }
        return task[1];
    }

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
