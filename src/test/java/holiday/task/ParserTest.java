package holiday.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import holiday.BotException;
import holiday.parser.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDeadlineArgs_validArgs_success() throws BotException {
        String input = "deadline submit report /by 2024-12-31 2359";

        String[] result = Parser.parseDeadlineArgs(input);

        assertEquals("submit report", result[0]);
        assertEquals("2024-12-31 2359", result[1]);
    }

    @Test
    public void parseDeadlineArgs_emptyTask_throwsException() {
        String input = "deadline    ";

        BotException exception = org.junit.jupiter.api.Assertions.assertThrows(
                BotException.class, () -> Parser.parseDeadlineArgs(input)
        );

        assertEquals("task cannot be empty!!!", exception.getMessage());
    }

    @Test
    public void parseDeadlineArgs_invalidDateFormat_throwsException() {
        String input = "deadline submit report /by tomorrow";

        BotException exception = org.junit.jupiter.api.Assertions.assertThrows(
                BotException.class, () -> Parser.parseDeadlineArgs(input)
        );

        assertEquals("Deadline must be in the format YYYY-MM-DD HHMM (e.g., 2026-02-21 2359)", exception.getMessage());
    }
}
