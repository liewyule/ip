package holiday.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {

    @Test
    public void toString_done_correctFormat() {
        ToDos task = new ToDos("CS2103T assignment");
        task.mark();
        assertEquals("[T][X] CS2103T assignment", task.toString());
    }
}
