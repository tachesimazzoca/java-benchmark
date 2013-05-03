package com.github.tachesimazzoca.java.benchmark.task;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Task(description = "Load 4096 bytes file by byte stream")
public class ByteStreamTaskSuite implements TaskSuite {
    public Runnable[] getTasks() {
        String path = "4096B.txt";
        Runnable[] tasks = {
            new ByteStreamTask(path),
            new BufferedByteStreamTask(path)
        };
        return tasks;
    }

    @Task(description = "InputStream > ByteArrayOutputStream")
    public static class ByteStreamTask implements Runnable {
        private String path;

        private ByteStreamTask() {
        }

        public ByteStreamTask(String path) {
            this.path = path;
        }

        public void run() {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream(this.path);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                int b;
                while ((b = is.read()) != -1) {
                    baos.write(b);
                }

                baos.close();
                is.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Task(description = "BufferedInputStream(InputStream) > BufferedOutputStream")
    public static class BufferedByteStreamTask implements Runnable {
        private String path;

        private BufferedByteStreamTask() {
        }

        public BufferedByteStreamTask(String path) {
            this.path = path;
        }

        public void run() {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream(this.path);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(new ByteArrayOutputStream());

                byte[] buf = new byte[256];
                int len;
                while ((len = bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.flush();

                bos.close();
                bis.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
