package holiday.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void toString_time_correctFormat() {
        Event task = new Event("meeting", "2026-02-02 1400", "2026-02-02 1600");
        assertEquals("[E][ ] meeting (from: Feb 02 2026 1400 to: Feb 02 2026 1600)", task.toString());
    }
}
