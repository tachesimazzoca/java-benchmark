package com.github.tachesimazzoca.java.benchmark.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@Task(description = "Load 4096 bytes file by character stream")
public class CharacterStreamTaskSuite implements TaskSuite {
    public Runnable[] getTasks() {
        String path = "4096B.txt";
        Runnable[] tasks = {
            new CharacterStreamTask(path),
            new BufferedCharacterStreamTask(path)
        };
        return tasks;
    }

    @Task(description = "InputStreamReader > OutputStreamWriter")
    public static class CharacterStreamTask implements Runnable {
        private String path;

        private CharacterStreamTask() {
        }

        public CharacterStreamTask(String path) {
            this.path = path;
        }

        public void run() {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream(this.path);
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");

                int chr;
                while ((chr = isr.read()) != -1) {
                    osw.write(chr);
                }

                osw.close();
                isr.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Task(description = "BufferedReader > BufferedWriter (256 characters buffer)")
    public static class BufferedCharacterStreamTask implements Runnable {
        private String path;

        private BufferedCharacterStreamTask() {
        }

        public BufferedCharacterStreamTask(String path) {
            this.path = path;
        }

        public void run() {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream(this.path);
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(baos, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);

                char[] buf = new char[256];
                int len;
                while ((len = br.read(buf)) != -1) {
                    bw.write(buf, 0, len);
                }
                bw.flush();

                bw.close();
                br.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
