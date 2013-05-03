package com.github.tachesimazzoca.java.benchmark;

import com.github.tachesimazzoca.java.benchmark.task.StringTaskSuite;
import com.github.tachesimazzoca.java.benchmark.task.ByteStreamTaskSuite;
import com.github.tachesimazzoca.java.benchmark.task.CharacterStreamTaskSuite;

public class Main {
    protected Main() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run(new StringTaskSuite());
        runner.run(new ByteStreamTaskSuite());
        runner.run(new CharacterStreamTaskSuite());
    }
}
