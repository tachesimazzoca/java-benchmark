package com.github.tachesimazzoca.java.benchmark.task;

@Task(description = "String manipulation")
public class StringTaskSuite implements TaskSuite {
    public Runnable[] getTasks() {
        return new Runnable[] {
            new StringAddTask(),
            new StringBuilderAppendTask(),
            new StringBufferAppendTask(),
        };
    }

    @Task(description = "Add new String instance for 10000 times")
    public static class StringAddTask implements Runnable {
        public void run() {
            String str = "";
            for (int i = 0; i < 10000; i++) {
                str += " ";
            }
        }
    }

    @Task(description = "Call StringBuilder#append for 10000 times")
    public static class StringBuilderAppendTask implements Runnable {
        public void run() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                sb.append(" ");
            }
        }
    }

    @Task(description = "Call StringBuffer#append for 10000 times")
    public static class StringBufferAppendTask implements Runnable {
        public void run() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10000; i++) {
                sb.append(" ");
            }
        }
    }
}
