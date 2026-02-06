package holiday.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toString_done_correctFormat() {
        ToDo task = new ToDo("CS2103T assignment");
        task.mark();
        assertEquals("[T][X] CS2103T assignment", task.toString());
    }
}
