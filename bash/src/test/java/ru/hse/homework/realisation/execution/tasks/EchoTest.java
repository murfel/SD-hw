package ru.hse.homework.realisation.execution.tasks;

import org.junit.Test;
import ru.hse.homework.interfaces.execution.Task;
import static org.junit.Assert.*;

public class EchoTest {
    @Test
    public void simpleTest() throws Exception {
        Task echoTask = new Echo();
        echoTask.setArgs(new String[]{"hello ", "world", "!"});
        String res = echoTask.execute();
        assertEquals("hello world!", res);
    }

    @Test(expected = Echo.EchoException.class)
    public void wringInputTest() throws Exception {
        Task echoTask = new Echo();
        echoTask.setArgs(new String[0]);
        echoTask.execute();
    }
}